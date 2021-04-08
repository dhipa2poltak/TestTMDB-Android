package com.dpfht.testtmdb

import android.app.Activity
import android.app.Application
import com.dpfht.testtmdb.di.ApplicationComponent
import com.dpfht.testtmdb.di.ApplicationModule
import com.dpfht.testtmdb.di.DaggerApplicationComponent

class TheApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        fun get(activity: Activity): TheApplication {
            return activity.application as TheApplication
        }
    }
}
