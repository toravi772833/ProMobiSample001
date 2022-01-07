package com.sample.app.view.fragments.details

import com.google.gson.Gson
import com.sample.app.R
import com.sample.app.common.CommonUtils
import com.sample.app.common.getTrim
import com.sample.app.components.base_components.BaseFragmentX
import com.sample.app.databinding.ReviewDetailsBinding
import com.sample.app.model.movie_review.MovieReviewModel

class ReviewDetailsFragment : BaseFragmentX<ReviewDetailsBinding>(R.layout.review_details) {

    private val reviewData: MovieReviewModel by lazy { Gson().fromJson(mActivity.exchangeDataString, MovieReviewModel::class.java) }

    override fun initComponents() {
        mBinding.fragment = this
        mBinding.data = reviewData
        mBinding.executePendingBindings()
    }

    fun onViewLinkClicked() {
        CommonUtils.openLinkInBrowser(mActivity, reviewData.link?.url.getTrim())
    }
}