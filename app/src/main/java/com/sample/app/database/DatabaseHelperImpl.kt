package com.sample.app.database

import com.sample.app.model.movie_review.MovieReviewModel
import javax.inject.Inject

class DatabaseHelperImpl @Inject constructor(val database: NewYorkTimesDB) : NewYorkTimesDAO {

    override suspend fun insertMovieReviews(movieReviews: List<MovieReviewModel>) = database.newYorkTimesDAO().insertMovieReviews(movieReviews)

    override suspend fun getPersistedData() = database.newYorkTimesDAO().getPersistedData()

    override suspend fun deleteAll() = database.newYorkTimesDAO().deleteAll()
}
