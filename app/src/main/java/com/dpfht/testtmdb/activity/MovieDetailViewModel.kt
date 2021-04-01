package com.dpfht.testtmdb.activity

import androidx.databinding.ObservableField
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.MovieDetailsResponse
import com.dpfht.testtmdb.rest.CallbackWrapper
import com.dpfht.testtmdb.rest.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MovieDetailViewModel: BaseViewModel() {

    var restApi: RestService? = null

    var id = -1

    var title = ObservableField<String>()
    var overview = ObservableField<String>()
    var posterPath = ObservableField<String>()

    val myCompositeDisposable = CompositeDisposable()

    fun doGetMovieDetail(movieId: Int) {
        isShowDialogLoading.postValue(true)

        val disposable = restApi?.getMovieDetail(movieId, Config.API_KEY)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : CallbackWrapper<Response<MovieDetailsResponse?>, MovieDetailsResponse?>(this) {
                override fun onSuccess(t: Response<MovieDetailsResponse?>) {
                    val movieDetailsResponse = t.body()
                    if (movieDetailsResponse != null) {
                        id = movieDetailsResponse.id
                        title.set(movieDetailsResponse.title)
                        overview.set(movieDetailsResponse.overview)

                        if (movieDetailsResponse.posterPath != null) {
                            val imageUrl: String =
                                Config.IMAGE_URL_BASE_PATH + movieDetailsResponse.posterPath

                            posterPath.set("")
                            posterPath.set(imageUrl)
                        }
                    }
                }
            })

        if (disposable != null) {
            myCompositeDisposable.add(disposable)
        }
    }
}
