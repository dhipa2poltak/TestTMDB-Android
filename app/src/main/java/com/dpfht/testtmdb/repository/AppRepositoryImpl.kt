package com.dpfht.testtmdb.repository

import com.dpfht.testtmdb.model.response.DiscoverMovieByGenreResponse
import com.dpfht.testtmdb.model.response.GenreResponse
import com.dpfht.testtmdb.model.response.MovieDetailsResponse
import com.dpfht.testtmdb.model.response.ReviewResponse
import com.dpfht.testtmdb.model.response.TrailerResponse
import com.dpfht.testtmdb.rest.RestService
import io.reactivex.Observable
import retrofit2.Response

class AppRepositoryImpl(val restService: RestService): AppRepository {

  override fun getMovieGenre(apiKey: String):  Observable<Response<GenreResponse?>>? {
    return restService.getMovieGenre(apiKey)
  }

  override fun getMoviesByGenre(apiKey: String, genreId: String, page: Int): Observable<Response<DiscoverMovieByGenreResponse?>>? {
    return restService.getMoviesByGenre(apiKey, genreId, page)
  }

  override fun getMovieDetail(movieId: Int, apiKey: String): Observable<Response<MovieDetailsResponse?>>? {
    return restService.getMovieDetail(movieId, apiKey)
  }

  override fun getMovieReviews(movieId: Int, apiKey: String, page: Int): Observable<Response<ReviewResponse?>>? {
    return restService.getMovieReviews(movieId, apiKey, page)
  }

  override fun getMovieTrailers(movieId: Int, apiKey: String): Observable<Response<TrailerResponse?>>? {
    return restService.getMovieTrailers(movieId, apiKey)
  }
}
