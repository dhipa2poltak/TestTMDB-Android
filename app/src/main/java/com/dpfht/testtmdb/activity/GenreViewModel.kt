package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Genre
import com.dpfht.testtmdb.model.response.GenreResponse
import com.dpfht.testtmdb.repository.AppRepository
import com.dpfht.testtmdb.rest.CallbackWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class GenreViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel() {

    var genres: ArrayList<Genre> = ArrayList()
    val genreData = MutableLiveData<List<Genre>>()
    val movieByGenreActivity = MutableLiveData<Pair<Class<*>, Genre?>>()

    val myCompositeDisposable = CompositeDisposable()

    fun doGetMovieGenre() {
        isShowDialogLoading.postValue(true)
        val disposable = appRepository.getMovieGenre(Config.API_KEY)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : CallbackWrapper<Response<GenreResponse?>, GenreResponse?>(this) {
                    override fun onSuccess(t: Response<GenreResponse?>) {
                        val genreResponse = t.body()
                        val listGenre = genreResponse?.genres ?: ArrayList()
                        genres = ArrayList(listGenre)
                        genreData.postValue(genres)
                    }
                })

        if (disposable != null) {
            myCompositeDisposable.add(disposable)
        }
    }

    fun onClickCell(genre: Genre) {
        movieByGenreActivity.postValue(Pair(MovieByGenreActivity::class.java, genre))
    }
}
