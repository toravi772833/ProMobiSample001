package com.sample.app.view.fragments.movie_reviews

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.app.application.SampleApplication
import com.sample.app.common.CommonUtils
import com.sample.app.components.base_components.BaseViewModel
import com.sample.app.database.DatabaseHelperImpl
import com.sample.app.model.movie_review.MovieReviewModel
import com.sample.app.service_adapter.MovieReviewRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MovieReviewsViewModel @Inject constructor(@ApplicationContext private val application: Context) : BaseViewModel(application) {

    @Inject
    lateinit var databaseHelper: DatabaseHelperImpl
    val emptyViewMessage = ObservableField("")

    val detailsList = MutableLiveData<List<MovieReviewModel>>()
    val nextPageDetailsList = MutableLiveData<List<MovieReviewModel>>()

    val showBottomLoading = ObservableField(false)
    val showMainLoading = ObservableField(false)

    private val dataRepo by lazy { MovieReviewRepo(application, viewModelScope, databaseHelper) }

    fun getAllList(isSilent: Boolean = false) {
        if (!isSilent) showMainLoading.set(true)
        showBottomLoading.set(false)
        if (!CommonUtils.isNetworkAvailable(application) && detailsList.value?.size ?: 0 != 0) {
            showMainLoading.set(false)
            showBottomLoading.set(false)
            return
        }
        dataRepo.getAllMovieReviews(success = { data ->
            showMainLoading.set(false)
            emptyViewMessage.set("")
            detailsList.postValue(data)
        }, failure = { error ->
            showMainLoading.set(false)
            showSnackBar.postValue(error.message)
            emptyViewMessage.set(error.message)
        })

        viewModelScope
    }

    fun requestNextPageList() {
        if (!dataRepo.hasNextPage) return
        showMainLoading.set(false)
        showBottomLoading.set(true)
        if (!CommonUtils.isNetworkAvailable(application) && detailsList.value?.size ?: 0 != 0) {
            showMainLoading.set(false)
            showBottomLoading.set(false)
            return
        }
        dataRepo.getAllMovieReviews(isNextPageCall = true, success = { devices ->
            showBottomLoading.set(false)
            nextPageDetailsList.postValue(devices)
        }, failure = { error ->
            showBottomLoading.set(false)
            showSnackBar.postValue(error.message)
        })
    }
}
