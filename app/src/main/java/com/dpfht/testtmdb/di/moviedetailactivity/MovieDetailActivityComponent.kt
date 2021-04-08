package com.dpfht.testtmdb.di.moviedetailactivity

import com.dpfht.testtmdb.activity.MovieDetailActivity
import com.dpfht.testtmdb.di.ActivityScope
import com.dpfht.testtmdb.di.ApplicationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [MovieDetailActivityModule::class])
@MovieDetailActivityScope
@ActivityScope
interface MovieDetailActivityComponent {

    fun inject(movieDetailActivity: MovieDetailActivity)
}
