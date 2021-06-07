package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Genre
import com.dpfht.testtmdb.model.response.GenreResponse
import com.dpfht.testtmdb.net.ResultWrapper.GenericError
import com.dpfht.testtmdb.net.ResultWrapper.NetworkError
import com.dpfht.testtmdb.net.ResultWrapper.Success
import com.dpfht.testtmdb.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class GenreViewModel(private val appRepository: AppRepository): BaseViewModel() {

    var genres: ArrayList<Genre> = ArrayList()
    val genreData = MutableLiveData<List<Genre>>()
    val movieByGenreActivity = MutableLiveData<Pair<Class<*>, Genre?>>()

    fun doGetMovieGenre() {
        isShowDialogLoading.postValue(true)
        viewModelScope.launch(Dispatchers.Main) {
            when (val genreResponse = appRepository.getMovieGenre(Config.API_KEY)) {
                is NetworkError -> toastMessage.value = "network error"
                is GenericError -> toastMessage.value =
                    "error ${genreResponse.code} ${genreResponse.error?.statusMessage}"
                is Success -> doSuccess(genreResponse.value)
            }

            isShowDialogLoading.postValue(false)
            isLoadingData = false
        }
    }

    private fun doSuccess(genreResponse: GenreResponse) {
        val listGenre = genreResponse.genres ?: ArrayList()
        genres = ArrayList(listGenre)
        genreData.postValue(genres)
    }

    fun onClickCell(genre: Genre) {
        movieByGenreActivity.postValue(Pair(MovieByGenreActivity::class.java, genre))
    }
}
