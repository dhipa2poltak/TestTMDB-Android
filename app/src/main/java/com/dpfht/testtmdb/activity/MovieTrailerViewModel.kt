package com.dpfht.testtmdb.activity

import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Trailer
import com.dpfht.testtmdb.rest.RestService
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException

class MovieTrailerViewModel(private val restService: RestService) {

    var trailers: ArrayList<Trailer> = ArrayList()
    private var isLoadingData = false
    val myJob = Job()
    private val myScope = CoroutineScope(myJob)

    fun doGetMovieTrailers(movieId: Int, successCallback: () -> Unit/*, errorCallback: () -> Unit*/) {
        isLoadingData = true

        myScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) { restService.getMovieTrailers(movieId, Config.API_KEY) }
                if (response.results != null) {
                    trailers = response.results!!
                    successCallback()
                }
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> {
                        //toastMessage.postValue("Network Error")
                    }
                    is HttpException -> {
                        //val code = t.code()
                        //val errorResponse = t.message()
                        //toastMessage.postValue("Error $code $errorResponse")
                    }
                    else -> {
                        //toastMessage.postValue("Unknown Error")
                    }
                }
            } finally {
                //isShowDialogLoading.postValue(false)
                isLoadingData = false
            }
        }
    }
}