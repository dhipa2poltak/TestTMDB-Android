package com.dpfht.testtmdb.repository

import com.dpfht.testtmdb.model.response.DiscoverMovieByGenreResponse
import com.dpfht.testtmdb.model.response.GenreResponse
import com.dpfht.testtmdb.model.response.MovieDetailsResponse
import com.dpfht.testtmdb.model.response.ReviewResponse
import com.dpfht.testtmdb.model.response.TrailerResponse
import com.dpfht.testtmdb.net.ResultWrapper

interface AppRepository {

  suspend fun getMovieGenre(apiKey: String): ResultWrapper<GenreResponse>

  suspend fun getMoviesByGenre(apiKey: String, genreId: String, page: Int): ResultWrapper<DiscoverMovieByGenreResponse>

  suspend fun getMovieDetail(movieId: Int, apiKey: String): ResultWrapper<MovieDetailsResponse>

  suspend fun getMovieReviews(movieId: Int, apiKey: String, page: Int, language: String = "en-US"): ResultWrapper<ReviewResponse>

  suspend fun getMovieTrailers(movieId: Int, apiKey: String, language: String = "en-US"): ResultWrapper<TrailerResponse>
}
