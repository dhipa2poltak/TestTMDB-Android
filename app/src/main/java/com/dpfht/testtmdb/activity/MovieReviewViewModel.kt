package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Review
import com.dpfht.testtmdb.model.response.ReviewResponse
import com.dpfht.testtmdb.repository.AppRepository
import com.dpfht.testtmdb.rest.CallbackWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieReviewViewModel @Inject constructor(private val appRepository: AppRepository): BaseViewModel() {

    var reviews: ArrayList<Review> = ArrayList()
    val reviewData = MutableLiveData<Review>()

    var page = 1
    var movieId = -1

    val myCompositeDisposable = CompositeDisposable()

    fun doGetMovieReviews(movieId: Int, page: Int) {
        isShowDialogLoading.postValue(true)
        isLoadingData = true

        val disposable = appRepository.getMovieReviews(movieId, Config.API_KEY, page)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : CallbackWrapper<Response<ReviewResponse?>, ReviewResponse?>(this) {
                override fun onSuccess(t: Response<ReviewResponse?>) {
                    val reviewResponse = t.body()
                    if (reviewResponse?.results != null && reviewResponse.results!!.isNotEmpty()) {
                        for (review in reviewResponse.results!!) {
                            reviews.add(review)
                            reviewData.value = review
                        }

                        this@MovieReviewViewModel.page = page
                    }
                }
            })

        if (disposable != null) {
            myCompositeDisposable.add(disposable)
        }
    }
}
