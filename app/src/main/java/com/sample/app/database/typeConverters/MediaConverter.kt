package com.sample.app.database.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sample.app.model.movie_review.Multimedia

class MediaConverter {

    @TypeConverter
    fun toStr(media: Multimedia) = Gson().toJson(media)

    @TypeConverter
    fun toLink(value: String?) = if (value == null) null else Gson().fromJson(value, Multimedia::class.java)
}