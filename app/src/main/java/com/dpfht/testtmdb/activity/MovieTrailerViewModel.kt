package com.dpfht.testtmdb.activity

import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Trailer
import com.dpfht.testtmdb.model.TrailerResponse
import com.dpfht.testtmdb.rest.RestClient
import com.dpfht.testtmdb.rest.RestService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieTrailerViewModel {

    var trailers: ArrayList<Trailer> = ArrayList()

    fun doGetMovieTrailers(movieId: Int, successCallback: () -> Unit, errorCallback: () -> Unit) {
        RestClient.client?.create(RestService::class.java)?.getMovieTrailers(movieId, Config.API_KEY)?.enqueue(object :
            Callback<TrailerResponse?> {
            override fun onFailure(call: Call<TrailerResponse?>?, t: Throwable?) {
                errorCallback()
            }

            override fun onResponse(
                call: Call<TrailerResponse?>?,
                response: Response<TrailerResponse?>?
            ) {
                val trailerResponse = response?.body()
                if (trailerResponse?.results != null) {
                    trailers = trailerResponse.results!!
                    successCallback()
                }
            }
        })
    }
}