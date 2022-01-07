package com.sample.app.view.fragments.movie_reviews

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.sample.app.R
import com.sample.app.application.SampleApplication
import com.sample.app.common.Alert
import com.sample.app.common.CommonUtils
import com.sample.app.common.getViewModel
import com.sample.app.components.base_components.BaseFragmentX
import com.sample.app.databinding.ReviewsListScreenBinding
import com.sample.app.model.movie_review.MovieReviewModel
import com.sample.app.view.adapter.MovieReviewsAdapter

class ReviewsFragment : BaseFragmentX<ReviewsListScreenBinding>(R.layout.reviews_list_screen) {

    private val viewModel by lazy { getViewModel(this, MovieReviewsViewModel(mActivity.application as SampleApplication)) }
    private val adapter by lazy { MovieReviewsAdapter(mActivity, viewModel::requestNextPageList, ::openDetailsView) }

    override fun initComponents() {
        mBinding.viewModel = viewModel
        mBinding.fragment = this
        addSubscribers()

        mBinding.rvAllDetails.layoutManager = LinearLayoutManager(mActivity)
        mBinding.rvAllDetails.adapter = adapter

        mBinding.srlAllDetails.setOnRefreshListener {
            if (!CommonUtils.isNetworkAvailable(mActivity)) mBinding.srlAllDetails.isRefreshing = false
            viewModel.getAllList(isSilent = true)
        }

        viewModel.getAllList(isSilent = false)
    }

    private fun addSubscribers() {

        viewModel.detailsList.observe(this, { details ->
            mBinding.srlAllDetails.isRefreshing = false
            details?.let { adapter.resetAllDetails(it) }
        })

        viewModel.nextPageDetailsList.observe(this, { details ->
            mBinding.srlAllDetails.isRefreshing = false
            details?.let { adapter.updateList(it) }
        })

        viewModel.showAlert.observe(this, { message ->
            message?.let { Alert.showAlert(mActivity, it) }
        })

        viewModel.showSnackBar.observe(this, { message ->
            message?.let {
                mBinding.srlAllDetails.isRefreshing = false
                Alert.showSnackBar(mBinding.root, it)
            }
        })
    }


    /**
     * Project unable to build with Safe args some Android open issue with Latest gradel plug in
     *
     *    Due to time constrain unable to fix the Safe args issue, that's the reason tweaking the argument passing mechanism.
     *
     *    Refer Link: https://github.com/android/sunflower/issues/714
     *
     */
    private fun openDetailsView(reviewDetails: MovieReviewModel) {
        mActivity.exchangeDataString = Gson().toJson(reviewDetails)
        findNavController().navigate(R.id.reviewDetailsFragment)
    }

    override fun onBackPressed(): Boolean {
        mActivity.finishAffinity()
        return true
    }
}