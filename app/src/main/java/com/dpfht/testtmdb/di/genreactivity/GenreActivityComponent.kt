package com.dpfht.testtmdb.di.genreactivity

import com.dpfht.testtmdb.activity.GenreActivity
import com.dpfht.testtmdb.di.ActivityScope
import com.dpfht.testtmdb.di.ApplicationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [GenreActivityModule::class])
@GenreActivityScope
@ActivityScope
interface GenreActivityComponent {

    fun inject(genreActivity: GenreActivity)
}