package com.dpfht.testtmdb.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.model.Review
import com.dpfht.testtmdb.model.response.ReviewResponse
import com.dpfht.testtmdb.net.ResultWrapper.GenericError
import com.dpfht.testtmdb.net.ResultWrapper.NetworkError
import com.dpfht.testtmdb.net.ResultWrapper.Success
import com.dpfht.testtmdb.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieReviewViewModel(private val appRepository: AppRepository): BaseViewModel() {

    var reviews: ArrayList<Review> = ArrayList()
    val reviewData = MutableLiveData<Review>()

    var page = 1
    var movieId = -1

    fun doGetMovieReviews(movieId: Int, page: Int) {
        isShowDialogLoading.postValue(true)
        isLoadingData = true

        viewModelScope.launch(Dispatchers.Main) {
            when (val response = appRepository.getMovieReviews(movieId, Config.API_KEY, page)) {
                is NetworkError -> toastMessage.value = "network error"
                is GenericError -> toastMessage.value =
                    "error ${response.code} ${response.error?.statusMessage}"
                is Success -> doSuccess(response.value, page)
            }

            isShowDialogLoading.postValue(false)
            isLoadingData = false
        }
    }

    private fun doSuccess(response: ReviewResponse, page: Int) {
        if (response.results != null && response.results!!.isNotEmpty()) {
            for (review in response.results!!) {
                reviews.add(review)
                reviewData.value = review
            }

            this@MovieReviewViewModel.page = page
        }
    }
}
