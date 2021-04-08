package com.dpfht.testtmdb.rest

import com.dpfht.testtmdb.model.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestService {

    @GET("genre/movie/list")
    fun getMovieGenre(@Query("api_key") apiKey: String):  Observable<Response<GenreResponse?>>?

    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: String,
        @Query("page") page: Int): Observable<Response<DiscoverMovieByGenreResponse?>>?

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String): Observable<Response<MovieDetailsResponse?>>?

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"): Observable<Response<ReviewResponse?>>?

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailers(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"): Observable<Response<TrailerResponse?>>?
}
