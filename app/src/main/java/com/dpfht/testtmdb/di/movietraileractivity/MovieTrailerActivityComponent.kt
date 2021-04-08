package com.dpfht.testtmdb.di.movietraileractivity

import com.dpfht.testtmdb.activity.MovieTrailerActivity
import com.dpfht.testtmdb.di.ApplicationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [MovieTrailerActivityModule::class])
@MovieTrailerActivityScope
interface MovieTrailerActivityComponent {

    fun inject(movieTrailerActivity: MovieTrailerActivity)
}