package com.dpfht.testtmdb.rest

import com.dpfht.testtmdb.activity.BaseViewModel
import io.reactivex.observers.DisposableObserver
import retrofit2.Response

abstract class CallbackWrapper<T: Response<r>?, r>(private val viewModel: BaseViewModel): DisposableObserver<T>() {

    protected abstract fun onSuccess(t: T)

    override fun onNext(t: T) {
        viewModel.isShowDialogLoading.postValue(false)
        viewModel.isLoadingData = false

        if (t?.isSuccessful() == true) {
            onSuccess(t)
        } else {
            viewModel.toastMessage.postValue("failed to load data")
        }
    }

    override fun onError(e: Throwable) {
        viewModel.isShowDialogLoading.postValue(false)
        viewModel.isLoadingData = false

        viewModel.toastMessage.postValue("failed to load data")
    }

    override fun onComplete() {

    }
}
