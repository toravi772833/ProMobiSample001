package com.sample.app.model.movie_review

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("url") val url: String?,
    @SerializedName("suggested_link_text") val suggestedLinkText: String?,
    @SerializedName("type") val type: String?,
)
