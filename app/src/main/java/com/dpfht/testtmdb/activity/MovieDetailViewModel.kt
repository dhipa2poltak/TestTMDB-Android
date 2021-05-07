package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.rest.RestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MovieDetailViewModel(private val restService: RestService): BaseViewModel() {

    var id = -1

    var title = MutableLiveData<String>()
    var overview = MutableLiveData<String>()
    var posterPath = MutableLiveData<String>()

    fun doGetMovieDetail(movieId: Int) {
        isShowDialogLoading.postValue(true)
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) { restService.getMovieDetail(movieId, Config.API_KEY) }
                id = response.id
                title.value = response.title
                overview.value = response.overview

                if (response.posterPath != null) {
                    val imageUrl: String =
                        Config.IMAGE_URL_BASE_PATH + response.posterPath

                    posterPath.value = ""
                    posterPath.value = imageUrl
                }
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> {
                        toastMessage.postValue("Network Error")
                    }
                    is HttpException -> {
                        val code = t.code()
                        val errorResponse = t.message()
                        toastMessage.postValue("Error $code $errorResponse")
                    }
                    else -> {
                        toastMessage.postValue("Unknown Error")
                    }
                }
            } finally {
                isShowDialogLoading.postValue(false)
                isLoadingData = false
            }

        }
    }
}
