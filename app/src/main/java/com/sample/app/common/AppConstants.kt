package com.sample.app.common

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object AppConstants {
    /**
     * Database name
     */
    object Database {
        const val DATABASE_NAME = "new_york_times_sample_database"
    }


    object AppTimeFormat {
        const val SERVER_DATE_FORMAT = "yyyy-mm-dd"
        const val SERVER_DATE_TIME_FORMAT = "yyyy-mm-dd HH:mm:ss"
        const val DISPLAY_DATE_FORMAT = "dd MMM yyyy"
        const val DISPLAY_DATE_TIME_FORMAT = "dd MMMM yyyy hh:mm a"
    }

    @JvmStatic
    @BindingAdapter("profileImage")
    fun loadImage(view: AppCompatImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(imageUrl).apply(RequestOptions().centerCrop())
                .into(view)
        }
    }
}