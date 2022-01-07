package com.sample.app.model


import com.google.gson.annotations.SerializedName

data class LoadMoreResponse<T>(
    @SerializedName("copyright") val copyright: String,
    @SerializedName("has_more") val hasMore: Boolean,
    @SerializedName("num_results") val numResults: Int,
    @SerializedName("status") val status: String,
    @SerializedName("results") val results: List<T> ?
)