package com.dpfht.testtmdb.rest

import com.dpfht.testtmdb.model.response.DiscoverMovieByGenreResponse
import com.dpfht.testtmdb.model.response.GenreResponse
import com.dpfht.testtmdb.model.response.MovieDetailsResponse
import com.dpfht.testtmdb.model.response.ReviewResponse
import com.dpfht.testtmdb.model.response.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestService {

    @GET("genre/movie/list")
    suspend fun getMovieGenre(@Query("api_key") apiKey: String): GenreResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: String,
        @Query("page") page: Int): DiscoverMovieByGenreResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String): MovieDetailsResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"): ReviewResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"): TrailerResponse
}
