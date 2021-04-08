package com.dpfht.testtmdb.di.moviebygenreactivity

import com.dpfht.testtmdb.activity.MovieByGenreActivity
import com.dpfht.testtmdb.di.ActivityScope
import com.dpfht.testtmdb.di.ApplicationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [MovieByGenreActivityModule::class])
@MovieByGenreActivityScope
@ActivityScope
interface MovieByGenreActivityComponent {

    fun inject(movieByGenreActivity: MovieByGenreActivity)
}
