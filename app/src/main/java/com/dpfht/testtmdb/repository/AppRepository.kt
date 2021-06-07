package com.dpfht.testtmdb.repository

import com.dpfht.testtmdb.model.response.DiscoverMovieByGenreResponse
import com.dpfht.testtmdb.model.response.GenreResponse
import com.dpfht.testtmdb.model.response.MovieDetailsResponse
import com.dpfht.testtmdb.model.response.ReviewResponse
import com.dpfht.testtmdb.model.response.TrailerResponse
import io.reactivex.Observable
import retrofit2.Response

interface AppRepository {

  fun getMovieGenre(apiKey: String):  Observable<Response<GenreResponse?>>?

  fun getMoviesByGenre(apiKey: String, genreId: String, page: Int): Observable<Response<DiscoverMovieByGenreResponse?>>?

  fun getMovieDetail(movieId: Int, apiKey: String): Observable<Response<MovieDetailsResponse?>>?

  fun getMovieReviews(movieId: Int, apiKey: String, page: Int): Observable<Response<ReviewResponse?>>?

  fun getMovieTrailers(movieId: Int, apiKey: String): Observable<Response<TrailerResponse?>>?
}
