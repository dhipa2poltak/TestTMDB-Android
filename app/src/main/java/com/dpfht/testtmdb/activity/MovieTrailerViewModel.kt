package com.dpfht.testtmdb.activity

import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Trailer
import com.dpfht.testtmdb.model.TrailerResponse
import com.dpfht.testtmdb.rest.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MovieTrailerViewModel(private val restService: RestService) {

    var trailers: ArrayList<Trailer> = ArrayList()
    var isLoadingData = false
    val myCompositeDisposable = CompositeDisposable()

    fun doGetMovieTrailers(movieId: Int, successCallback: () -> Unit/*, errorCallback: () -> Unit*/) {
        isLoadingData = true

        val disposable = restService.getMovieTrailers(movieId, Config.API_KEY)
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

        /*
        RestClient.client?.create(RestService::class.java)?.getMovieTrailers(movieId, Config.API_KEY)?.enqueue(object :
            Callback<TrailerResponse?> {
            override fun onFailure(call: Call<TrailerResponse?>?, t: Throwable?) {
                errorCallback()
            }

            override fun onResponse(
                call: Call<TrailerResponse?>?,
                response: Response<TrailerResponse?>?
            ) {
                val trailerResponse = response?.body()
                if (trailerResponse?.results != null) {
                    trailers = trailerResponse.results!!
                    successCallback()
                }
            }
        })
        */
    }
}