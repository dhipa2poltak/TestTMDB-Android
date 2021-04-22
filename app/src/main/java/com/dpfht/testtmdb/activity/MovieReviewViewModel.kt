package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Review
import com.dpfht.testtmdb.rest.RestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MovieReviewViewModel(private val restService: RestService): BaseViewModel() {

    var reviews: ArrayList<Review> = ArrayList()
    val reviewData = MutableLiveData<Review>()

    var page = 1
    var movieId = -1

    fun doGetMovieReviews(movieId: Int, page: Int) {
        isShowDialogLoading.postValue(true)
        isLoadingData = true

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = restService.getMovieReviews(movieId, Config.API_KEY, page)
                    if (response.results != null && response.results!!.isNotEmpty()) {
                        launch(Dispatchers.Main) {
                            for (review in response.results!!) {
                                reviews.add(review)
                                reviewData.value = review
                            }

                            this@MovieReviewViewModel.page = page
                        }
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
    }
}
