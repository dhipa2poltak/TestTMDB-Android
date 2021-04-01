package com.dpfht.testtmdb.activity

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.DiscoverMovieByGenreResponse
import com.dpfht.testtmdb.model.Movie
import com.dpfht.testtmdb.rest.CallbackWrapper
import com.dpfht.testtmdb.rest.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.ArrayList

class MovieByGenreViewModel: BaseViewModel() {

    var restApi: RestService? = null
    var genreId = -1
    val title = ObservableField<String>("")
    var movies: ArrayList<Movie> = ArrayList()
    val movieData = MutableLiveData<Movie>()
    var page = 1

    val movieDetailActivity = MutableLiveData<Pair<Class<*>, Movie?>>()

    val myCompositeDisposable = CompositeDisposable()

    fun doGetMoviesByGenre(genreId: String, page: Int) {
        isShowDialogLoading.postValue(true)
        isLoadingData = true
        val disposable = restApi?.getMoviesByGenre(Config.API_KEY, genreId, page)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object: CallbackWrapper<Response<DiscoverMovieByGenreResponse?>, DiscoverMovieByGenreResponse?>(this) {
                override fun onSuccess(t: Response<DiscoverMovieByGenreResponse?>) {
                    val moviesByGenreResponse = t.body()
                    if (moviesByGenreResponse?.results != null && moviesByGenreResponse.results!!.isNotEmpty()) {
                        for (movie in moviesByGenreResponse.results!!) {
                            movies.add(movie)
                            movieData.postValue(movie)
                        }
                        //movieData.postValue(movies)

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
