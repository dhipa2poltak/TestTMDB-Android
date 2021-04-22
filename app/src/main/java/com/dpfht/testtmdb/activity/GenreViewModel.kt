package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Genre
import com.dpfht.testtmdb.rest.RestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import kotlin.collections.ArrayList

class GenreViewModel(private val restService: RestService): BaseViewModel() {

    var genres: ArrayList<Genre> = ArrayList()
    val genreData = MutableLiveData<List<Genre>>()
    val movieByGenreActivity = MutableLiveData<Pair<Class<*>, Genre?>>()

    fun doGetMovieGenre() {
        isShowDialogLoading.postValue(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val genreResponse = restService.getMovieGenre(Config.API_KEY)
                    val listGenre = genreResponse.genres ?: ArrayList()
                    genres = ArrayList(listGenre)
                    genreData.postValue(genres)
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

    fun onClickCell(genre: Genre) {
        movieByGenreActivity.postValue(Pair(MovieByGenreActivity::class.java, genre))
    }
}
