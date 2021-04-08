package com.dpfht.testtmdb.di

import com.dpfht.testtmdb.rest.RestService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun getRestApiService(): RestService
}