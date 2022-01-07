package com.sample.app.model.movie_review


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sample.app.common.DateFormatHelper
import com.sample.app.common.getTrim
import com.sample.app.common.isNotEmpty
import com.sample.app.database.typeConverters.LinkConverter
import com.sample.app.database.typeConverters.MediaConverter

@Entity(tableName = "MovieReview")
data class MovieReviewModel(

    @PrimaryKey
    @SerializedName("display_title") val displayTitle: String,
    @SerializedName("byline") val byline: String?,
    @SerializedName("critics_pick") val criticsPick: Int?,
    @SerializedName("date_updated") val dateUpdated: String?,
    @SerializedName("headline") val headline: String?,
    @SerializedName("mpaa_rating") val mpaaRating: String?,
    @SerializedName("opening_date") val openingDate: String?,
    @SerializedName("publication_date") val publicationDate: String?,
    @SerializedName("summary_short") val summaryShort: String?,

    @TypeConverters(LinkConverter::class)
    @SerializedName("link") val link: Link?,

    @TypeConverters(MediaConverter::class)
    @SerializedName("multimedia") val multimedia: Multimedia?
) {

    val displayPublishDate: String
        get() = DateFormatHelper.getDisplayDateFromServerDate(dateUpdated)

    val displayOpeningDate: String
        get() = DateFormatHelper.getDisplayDateFromServerDate(openingDate)

    val displayUpdatedDate: String
        get() = DateFormatHelper.getDisplayDateTimeFromServerDate(dateUpdated)

    val buttonTitle: String
        get() = if (link?.suggestedLinkText.isNotEmpty()) link?.suggestedLinkText.getTrim()
            .replace("Read the New York Times", "View") else "View Link"

}