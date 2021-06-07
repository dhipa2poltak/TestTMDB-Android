package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.response.DiscoverMovieByGenreResponse
import com.dpfht.testtmdb.model.Movie
import com.dpfht.testtmdb.repository.AppRepository
import com.dpfht.testtmdb.rest.CallbackWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class MovieByGenreViewModel @Inject constructor(private val appRepository: AppRepository): BaseViewModel() {

    var genreId = -1
    val title = MutableLiveData<String>()
    var movies: ArrayList<Movie> = ArrayList()
    val movieData = MutableLiveData<Movie>()
    var page = 1

    val movieDetailActivity = MutableLiveData<Pair<Class<*>, Movie?>>()

    val myCompositeDisposable = CompositeDisposable()

    fun doGetMoviesByGenre(genreId: String, page: Int) {
        isShowDialogLoading.postValue(true)
        isLoadingData = true
        val disposable = appRepository.getMoviesByGenre(Config.API_KEY, genreId, page)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object: CallbackWrapper<Response<DiscoverMovieByGenreResponse?>, DiscoverMovieByGenreResponse?>(this) {
                override fun onSuccess(t: Response<DiscoverMovieByGenreResponse?>) {
                    val moviesByGenreResponse = t.body()
                    if (moviesByGenreResponse?.results != null && moviesByGenreResponse.results!!.isNotEmpty()) {
                        for (movie in moviesByGenreResponse.results!!) {
                            movies.add(movie)
                            movieData.value = movie
                        }

                        this@MovieByGenreViewModel.page = page
                    }
                }
            })

        if (disposable != null) {
            myCompositeDisposable.add(disposable)
        }
    }

    fun onClickCell(movie: Movie) {
        movieDetailActivity.postValue(Pair(MovieDetailActivity::class.java, movie))
    }
}
