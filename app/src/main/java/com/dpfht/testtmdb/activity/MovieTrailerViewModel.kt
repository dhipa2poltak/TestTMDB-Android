package com.dpfht.testtmdb.activity

import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Trailer
import com.dpfht.testtmdb.model.response.TrailerResponse
import com.dpfht.testtmdb.rest.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MovieTrailerViewModel {

    lateinit var restApi: RestService

    var trailers: ArrayList<Trailer> = ArrayList()

    //val isShowDialogLoading = MutableLiveData<Boolean>()
    var isLoadingData = false
    //val toastMessage = MutableLiveData<String>()

    val myCompositeDisposable = CompositeDisposable()

    fun doGetMovieTrailers(movieId: Int, successCallback: () -> Unit) {
        //isShowDialogLoading.postValue(true)
        isLoadingData = true

        val disposable = restApi.getMovieTrailers(movieId, Config.API_KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableObserver<Response<TrailerResponse?>>() {
                fun onSuccess(t: Response<TrailerResponse?>) {
                    val trailerResponse = t.body()
                    if (trailerResponse?.results != null) {
                        trailers = trailerResponse.results!!
                        successCallback()
                    }
                }

                override fun onNext(t: Response<TrailerResponse?>) {
                    //isShowDialogLoading.postValue(false)
                    isLoadingData = false

                    if (t.isSuccessful) {
                        onSuccess(t)
                    } /*else {
                        //toastMessage.postValue("failed to load data")
                    }
                    */
                }

                override fun onError(e: Throwable) {
                    //isShowDialogLoading.postValue(false)
                    isLoadingData = false

                    //toastMessage.postValue("failed to load data")
                }

                override fun onComplete() {

                }
            })

        if (disposable != null) {
            myCompositeDisposable.add(disposable)
        }
    }
}
