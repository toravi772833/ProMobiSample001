package com.sample.app.service_adapter

import android.content.Context
import com.sample.app.BuildConfig
import com.sample.app.common.CommonUtils
import com.sample.app.database.DatabaseHelperImpl
import com.sample.app.model.movie_review.MovieReviewModel
import com.sample.app.network.APIClient
import com.sample.app.network.APIConstants
import com.sample.app.network.APIRequest
import com.sample.app.network.error.ErrorHandling
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@ViewModelScoped
class MovieReviewRepo @Inject constructor(
    private val application: Context,
    private val coroutineScope: CoroutineScope,
    private val databaseHelper: DatabaseHelperImpl
) {

    private var currentOffset = 0
    var hasNextPage = true

    /**
     * This act like an REPO
     *
     *   >> It will fetch the details from API.
     *   >> First set of fresh list will be stored in ROOM to work app in offline mode.
     *   >> If Internet not available it return latest stored data from ROOM.
     *   >> Every refresh of list first set will be captured in ROOM.
     *
     */
    fun getAllMovieReviews(
        isNextPageCall: Boolean = false,
        success: (List<MovieReviewModel>) -> Unit,
        failure: (Error) -> Unit
    ) {
        if (CommonUtils.isNetworkAvailable(application)) {
            if (!isNextPageCall) currentOffset = 0

            val queryMap = HashMap<String, String>()
            queryMap[APIConstants.CommonParams.KEY_ORDER] = APIConstants.MovieReviewValues.BY_PUBLICATION_DATE
            queryMap[APIConstants.CommonParams.KEY_OFFSET] = currentOffset.toString()

            val requestClient = APIClient.getRetrofitAdapter(BuildConfig.BASE_URL).create(APIRequest::class.java)
            requestClient.getAllMovieReviews(type = APIConstants.MovieReviewValues.ALL, queryMap = queryMap).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    hasNextPage = response.hasMore == true
                    if (response.results != null && response.results.isNotEmpty()) {
                        coroutineScope.launch {
                            if (currentOffset == 0) databaseHelper.deleteAll()
                            databaseHelper.insertMovieReviews(response.results)
                            currentOffset = currentOffset.plus(response.numResults)
                            success(response.results)
                        }
                    } else failure(Error("No reviews found..!"))
                }) { exception -> failure(handleCommonError(exception)) }
        } else {
            coroutineScope.launch {
                val results = databaseHelper.getPersistedData()
                if (results.isNotEmpty()) success(results)
                else failure(ErrorHandling.getNoNetworkError())
            }
        }
    }

    private fun handleCommonError(e: Throwable?): Error {

        var message = "Something went wrong, Please try again later."

        return if (e != null && e is HttpException) {
            try {
                val errorBody = e.response()
                errorBody?.let {
                    val jsonBody = JSONObject(it.errorBody()!!.string())
                    message = jsonBody.getJSONObject("fault").getString("faultstring")
                }
            } catch (ex: Exception) {
                message = ex.message.toString()
            }
            Error(message)
        } else Error(message)
    }
}
