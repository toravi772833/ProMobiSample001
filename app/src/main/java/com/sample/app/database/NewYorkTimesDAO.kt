package com.sample.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.app.model.movie_review.MovieReviewModel

@Dao
interface NewYorkTimesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieReviews(movieReviews: List<MovieReviewModel>)

    @Query("SELECT * FROM MovieReview")
    suspend fun getPersistedData(): List<MovieReviewModel>

    @Query("DELETE FROM MovieReview")
    suspend fun deleteAll()
}