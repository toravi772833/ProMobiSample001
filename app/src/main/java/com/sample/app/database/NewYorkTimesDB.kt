package com.sample.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.app.database.typeConverters.LinkConverter
import com.sample.app.database.typeConverters.MediaConverter
import com.sample.app.model.movie_review.MovieReviewModel

@Database(
    entities = [MovieReviewModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LinkConverter::class, MediaConverter::class)
abstract class NewYorkTimesDB : RoomDatabase() {

    abstract fun newYorkTimesDAO(): NewYorkTimesDAO
}
