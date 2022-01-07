package com.sample.app.database.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sample.app.model.movie_review.Link

class LinkConverter {

    @TypeConverter
    fun toStr(link: Link) = Gson().toJson(link)

    @TypeConverter
    fun toLink(value: String?) = if (value == null) null else Gson().fromJson(value, Link::class.java)
}
