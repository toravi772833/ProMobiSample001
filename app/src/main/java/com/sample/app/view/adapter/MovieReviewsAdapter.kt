package com.sample.app.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.common.CommonUtils
import com.sample.app.common.getTrim
import com.sample.app.databinding.ItemReviewBinding
import com.sample.app.model.movie_review.MovieReviewModel

class MovieReviewsAdapter(
    private val mContext: Context,
    private val onNextPageLoad: () -> Unit,
    private val callBack: (MovieReviewModel) -> Unit
) : RecyclerView.Adapter<MovieReviewsAdapter.MovieReviewHolder>() {

    private val allReviews = mutableListOf<MovieReviewModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun resetAllDetails(allDetails: List<MovieReviewModel>) {
        this.allReviews.clear()
        this.allReviews.addAll(allDetails)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(allDetails: List<MovieReviewModel>) {
        this.allReviews.addAll(allDetails)
        notifyDataSetChanged()
    }

    inner class MovieReviewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        init {

            binding.root.setOnClickListener { callBack(allReviews[absoluteAdapterPosition]) }

            binding.ibViewLink.setOnClickListener {
                CommonUtils.openLinkInBrowser(mContext, allReviews[absoluteAdapterPosition].link?.url.getTrim())
            }
        }

        fun bindData(data: MovieReviewModel) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int) =
        MovieReviewHolder(ItemReviewBinding.inflate(LayoutInflater.from(mContext), parent, false))

    override fun getItemCount() = allReviews.size

    override fun onBindViewHolder(holder: MovieReviewHolder, position: Int) {
        holder.bindData(allReviews[position])
        if (position == allReviews.size - 1) onNextPageLoad()
    }
}