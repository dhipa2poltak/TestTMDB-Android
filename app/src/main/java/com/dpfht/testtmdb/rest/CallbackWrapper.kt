package com.dpfht.testtmdb.rest

import com.dpfht.testtmdb.activity.BaseViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.observers.DisposableObserver
import retrofit2.Response
import java.nio.charset.Charset

abstract class CallbackWrapper<T: Response<r>?, r>(private val viewModel: BaseViewModel): DisposableObserver<T>() {

    protected abstract fun onSuccess(t: T)

    override fun onNext(t: T) {
        viewModel.isShowDialogLoading.postValue(false)
        viewModel.isLoadingData = false

        if (t?.isSuccessful == true) {
            onSuccess(t)
        } else {
            //viewModel.toastMessage.postValue("failed to load data")
            t?.errorBody()?.source().let {
                val json = it?.readString(Charset.defaultCharset())
                val typeToken = object : TypeToken<ErrorResponse>() {}.type
                val errorResponse = Gson().fromJson<ErrorResponse>(json, typeToken)

                viewModel.toastMessage.postValue("error ${t?.code()} ${errorResponse.statusMessage}")
            }
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
