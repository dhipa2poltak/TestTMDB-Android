package com.dpfht.testtmdb

import android.app.Application
import com.dpfht.testtmdb.di.module.appModule
import com.dpfht.testtmdb.di.module.myActivityModule
import com.dpfht.testtmdb.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, viewModelModule, myActivityModule))
        }
    }
}
