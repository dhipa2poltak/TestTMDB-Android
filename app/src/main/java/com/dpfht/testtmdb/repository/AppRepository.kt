package com.dpfht.testtmdb.repository

import com.dpfht.testtmdb.model.response.DiscoverMovieByGenreResponse
import com.dpfht.testtmdb.model.response.GenreResponse
import com.dpfht.testtmdb.model.response.MovieDetailsResponse
import com.dpfht.testtmdb.model.response.ReviewResponse
import com.dpfht.testtmdb.model.response.TrailerResponse
import com.dpfht.testtmdb.net.ResultWrapper
import com.dpfht.testtmdb.net.safeApiCall
import com.dpfht.testtmdb.rest.RestService
import kotlinx.coroutines.Dispatchers

class AppRepository(private val restService: RestService) {

  suspend fun getMovieGenre(apiKey: String): ResultWrapper<GenreResponse> {
    return safeApiCall(Dispatchers.IO) { restService.getMovieGenre(apiKey) }
  }

  suspend fun getMoviesByGenre(apiKey: String, genreId: String, page: Int): ResultWrapper<DiscoverMovieByGenreResponse> {
    return safeApiCall(Dispatchers.IO) { restService.getMoviesByGenre(apiKey, genreId, page) }
  }

  suspend fun getMovieDetail(movieId: Int, apiKey: String): ResultWrapper<MovieDetailsResponse> {
    return safeApiCall(Dispatchers.IO) { restService.getMovieDetail(movieId, apiKey) }
  }

  suspend fun getMovieReviews(movieId: Int, apiKey: String, page: Int, language: String = "en-US"): ResultWrapper<ReviewResponse> {
    return safeApiCall(Dispatchers.IO) { restService.getMovieReviews(movieId, apiKey, page, language) }
  }

  suspend fun getMovieTrailers(movieId: Int, apiKey: String, language: String = "en-US"): ResultWrapper<TrailerResponse> {
    return safeApiCall(Dispatchers.IO) { restService.getMovieTrailers(movieId, apiKey, language) }
  }
}
