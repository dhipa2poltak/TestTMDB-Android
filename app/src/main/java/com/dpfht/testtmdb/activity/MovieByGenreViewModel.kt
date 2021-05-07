package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Movie
import com.dpfht.testtmdb.rest.RestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.util.ArrayList

class MovieByGenreViewModel(private val restService: RestService): BaseViewModel() {

    var genreId = -1
    val title = MutableLiveData<String>()
    var movies: ArrayList<Movie> = ArrayList()
    val movieData = MutableLiveData<Movie>()
    var page = 1

    val movieDetailActivity = MutableLiveData<Pair<Class<*>, Movie?>>()

    fun doGetMoviesByGenre(genreId: String, page: Int) {
        isShowDialogLoading.postValue(true)
        isLoadingData = true

        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) { restService.getMoviesByGenre(Config.API_KEY, genreId, page) }
                if (response.results != null && response.results!!.isNotEmpty()) {
                    for (movie in response.results!!) {
                        movies.add(movie)
                        movieData.value = movie
                    }

                    this@MovieByGenreViewModel.page = page
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

    fun onClickCell(movie: Movie) {
        movieDetailActivity.postValue(Pair(MovieDetailActivity::class.java, movie))
    }
}
