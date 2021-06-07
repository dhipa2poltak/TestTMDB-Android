package com.dpfht.testtmdb.repository

import com.dpfht.testtmdb.model.response.DiscoverMovieByGenreResponse
import com.dpfht.testtmdb.model.response.GenreResponse
import com.dpfht.testtmdb.model.response.MovieDetailsResponse
import com.dpfht.testtmdb.model.response.ReviewResponse
import com.dpfht.testtmdb.model.response.TrailerResponse
import com.dpfht.testtmdb.rest.RestService
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response

class AppRepositoryImpl(private val restApi: RestService?): AppRepository {

  override fun getMovieGenre(apiKey: String):  Observable<Response<GenreResponse?>>? {
    return restApi?.getMovieGenre(apiKey)
  }

  override fun getMoviesByGenre(apiKey: String, genreId: String, page: Int): Observable<Response<DiscoverMovieByGenreResponse?>>? {
    return restApi?.getMoviesByGenre(apiKey, genreId, page)
  }

  override fun getMovieDetail(movieId: Int, apiKey: String): Observable<Response<MovieDetailsResponse?>>? {
    return restApi?.getMovieDetail(movieId, apiKey)
  }

  override fun getMovieReviews(movieId: Int, apiKey: String, page: Int): Observable<Response<ReviewResponse?>>? {
    return restApi?.getMovieReviews(movieId, apiKey, page)
  }

  override fun getMovieTrailers(movieId: Int, apiKey: String): Call<TrailerResponse?>? {
    return restApi?.getMovieTrailers(movieId, apiKey)
  }
}
