package com.sample.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.app.common.AppConstants
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

    companion object {

        private lateinit var INSTANCE: NewYorkTimesDB

        @Synchronized
        internal fun getDatabase(mContext: Context): NewYorkTimesDB {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(NewYorkTimesDB::class.java) {
                    if (!Companion::INSTANCE.isInitialized)
                        INSTANCE = Room.databaseBuilder(mContext.applicationContext, NewYorkTimesDB::class.java, AppConstants.Database.DATABASE_NAME).build()
                }
            }
            return INSTANCE
        }
    }
}