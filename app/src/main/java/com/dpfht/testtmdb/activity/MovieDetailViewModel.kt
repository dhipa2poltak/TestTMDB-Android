package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.response.MovieDetailsResponse
import com.dpfht.testtmdb.net.ResultWrapper.GenericError
import com.dpfht.testtmdb.net.ResultWrapper.NetworkError
import com.dpfht.testtmdb.net.ResultWrapper.Success
import com.dpfht.testtmdb.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val appRepository: AppRepository): BaseViewModel() {

    var id = -1

    var title = MutableLiveData<String>()
    var overview = MutableLiveData<String>()
    var posterPath = MutableLiveData<String>()

    fun doGetMovieDetail(movieId: Int) {
        isShowDialogLoading.postValue(true)
        viewModelScope.launch(Dispatchers.Main) {
            when (val response = appRepository.getMovieDetail(movieId, Config.API_KEY)) {
                is NetworkError -> toastMessage.value = "network error"
                is GenericError -> toastMessage.value =
                    "error ${response.code} ${response.error?.statusMessage}"
                is Success -> doSuccess(response.value)
            }

            isShowDialogLoading.postValue(false)
            isLoadingData = false
        }
    }

    private fun doSuccess(response: MovieDetailsResponse) {
        id = response.id
        title.value = response.title
        overview.value = response.overview

        if (response.posterPath != null) {
            val imageUrl: String =
                Config.IMAGE_URL_BASE_PATH + response.posterPath

            posterPath.value = ""
            posterPath.value = imageUrl
        }
    }
}
