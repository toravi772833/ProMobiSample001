package com.sample.app.network

import com.sample.app.model.LoadMoreResponse
import com.sample.app.model.movie_review.MovieReviewModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import rx.Observable

interface APIRequest {

    @GET("/svc/movies/v2/reviews/{type}.json")
    fun getAllMovieReviews(@Path("type") type: String, @QueryMap queryMap: HashMap<String, String>): Observable<LoadMoreResponse<MovieReviewModel>>
}