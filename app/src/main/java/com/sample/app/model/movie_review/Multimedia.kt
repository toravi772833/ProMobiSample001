package com.sample.app.model.movie_review


import com.google.gson.annotations.SerializedName

data class Multimedia(
    @SerializedName("src") val src: String?,
    @SerializedName("height") val height: Int?,
    @SerializedName("type") val type: String?,
    @SerializedName("width") val width: Int?,
)