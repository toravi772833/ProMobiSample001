package com.sample.app.database

import android.content.Context
import androidx.room.Room
import com.sample.app.common.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideNewYorkTimesDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NewYorkTimesDB::class.java, AppConstants.Database.DATABASE_NAME).build()
}
