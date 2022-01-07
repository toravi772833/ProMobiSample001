package com.sample.app.database

import com.sample.app.model.movie_review.MovieReviewModel

class DatabaseHelperImpl(private val database: NewYorkTimesDB) : NewYorkTimesDAO {

    override suspend fun insertMovieReviews(movieReviews: List<MovieReviewModel>) = database.newYorkTimesDAO().insertMovieReviews(movieReviews)

    override suspend fun getPersistedData() = database.newYorkTimesDAO().getPersistedData()

    override suspend fun deleteAll() = database.newYorkTimesDAO().deleteAll()

}