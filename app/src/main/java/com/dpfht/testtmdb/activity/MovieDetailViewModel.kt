package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.response.MovieDetailsResponse
import com.dpfht.testtmdb.repository.AppRepository
import com.dpfht.testtmdb.rest.CallbackWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val appRepository: AppRepository): BaseViewModel() {

    var id = -1

    var title = MutableLiveData<String>()
    var overview = MutableLiveData<String>()
    var posterPath = MutableLiveData<String>()

    val myCompositeDisposable = CompositeDisposable()

    fun doGetMovieDetail(movieId: Int) {
        isShowDialogLoading.postValue(true)

        val disposable = appRepository.getMovieDetail(movieId, Config.API_KEY)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : CallbackWrapper<Response<MovieDetailsResponse?>, MovieDetailsResponse?>(this) {
                override fun onSuccess(t: Response<MovieDetailsResponse?>) {
                    val movieDetailsResponse = t.body()
                    if (movieDetailsResponse != null) {
                        id = movieDetailsResponse.id
                        title.value = movieDetailsResponse.title
                        overview.value = movieDetailsResponse.overview

                        if (movieDetailsResponse.posterPath != null) {
                            val imageUrl: String =
                                Config.IMAGE_URL_BASE_PATH + movieDetailsResponse.posterPath

                            posterPath.value = ""
                            posterPath.value = imageUrl
                        }
                    }
                }
            })

        if (disposable != null) {
            myCompositeDisposable.add(disposable)
        }
    }
}
