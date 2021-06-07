package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Movie
import com.dpfht.testtmdb.model.response.DiscoverMovieByGenreResponse
import com.dpfht.testtmdb.net.ResultWrapper.GenericError
import com.dpfht.testtmdb.net.ResultWrapper.NetworkError
import com.dpfht.testtmdb.net.ResultWrapper.Success
import com.dpfht.testtmdb.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class MovieByGenreViewModel(private val appRepository: AppRepository): BaseViewModel() {

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
            when (val response = appRepository.getMoviesByGenre(Config.API_KEY, genreId, page)) {
                is NetworkError -> toastMessage.value = "network error"
                is GenericError -> toastMessage.value =
                    "error ${response.code} ${response.error?.statusMessage}"
                is Success -> doSuccess(response.value, page)
            }

            isShowDialogLoading.postValue(false)
            isLoadingData = false
        }
    }

    private fun doSuccess(response: DiscoverMovieByGenreResponse, page: Int) {
        if (response.results != null && response.results!!.isNotEmpty()) {
            for (movie in response.results!!) {
                movies.add(movie)
                movieData.value = movie
            }

            this@MovieByGenreViewModel.page = page
        }
    }

    fun onClickCell(movie: Movie) {
        movieDetailActivity.postValue(Pair(MovieDetailActivity::class.java, movie))
    }
}
