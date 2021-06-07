package com.dpfht.testtmdb.activity

import androidx.lifecycle.viewModelScope
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Trailer
import com.dpfht.testtmdb.model.response.TrailerResponse
import com.dpfht.testtmdb.net.ResultWrapper.GenericError
import com.dpfht.testtmdb.net.ResultWrapper.NetworkError
import com.dpfht.testtmdb.net.ResultWrapper.Success
import com.dpfht.testtmdb.repository.AppRepository
import kotlinx.coroutines.*

class MovieTrailerViewModel(private val appRepository: AppRepository): BaseViewModel() {

    var trailers: ArrayList<Trailer> = ArrayList()

    fun doGetMovieTrailers(movieId: Int, successCallback: () -> Unit) {
        isLoadingData = true

        viewModelScope.launch(Dispatchers.Main) {
            when (val response = appRepository.getMovieTrailers(movieId, Config.API_KEY)) {
                is NetworkError -> toastMessage.value = "network error"
                is GenericError -> toastMessage.value =
                    "error ${response.code} ${response.error?.statusMessage}"
                is Success -> doSuccess(response.value, successCallback)
            }

            isShowDialogLoading.postValue(false)
            isLoadingData = false
        }
    }

    private fun doSuccess(response: TrailerResponse, successCallback: () -> Unit) {
        if (response.results != null) {
            trailers = response.results!!
            successCallback()
        }
    }
}