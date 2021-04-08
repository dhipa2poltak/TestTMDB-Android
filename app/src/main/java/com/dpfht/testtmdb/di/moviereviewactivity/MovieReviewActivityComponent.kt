package com.dpfht.testtmdb.di.moviereviewactivity

import com.dpfht.testtmdb.activity.MovieReviewActivity
import com.dpfht.testtmdb.di.ActivityScope
import com.dpfht.testtmdb.di.ApplicationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [MovieReviewActivityModule::class])
@MovieReviewActivityScope
@ActivityScope
interface MovieReviewActivityComponent {

    fun inject(movieReviewActivity: MovieReviewActivity)
}
