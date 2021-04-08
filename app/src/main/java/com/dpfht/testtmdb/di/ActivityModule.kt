package com.dpfht.testtmdb.di

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.dpfht.testtmdb.R
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideLoadingDialog(@ActivityContext context: Context): AlertDialog {
        return AlertDialog.Builder(context)
            .setCancelable(false)
            .setView(R.layout.dialog_loading)
            .create()
    }
}
