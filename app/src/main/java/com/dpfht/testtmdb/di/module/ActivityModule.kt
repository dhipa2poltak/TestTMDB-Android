package com.dpfht.testtmdb.di.module

import android.app.Activity
import android.content.Context
import androidx.databinding.DataBindingUtil
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.databinding.ActivityGenreBinding
import com.dpfht.testtmdb.databinding.ActivityMovieByGenreBinding
import com.dpfht.testtmdb.databinding.ActivityMovieDetailBinding
import com.dpfht.testtmdb.databinding.ActivityMovieReviewBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideActivityGenreBinding(@ActivityContext context: Context): ActivityGenreBinding {
        return DataBindingUtil.setContentView(context as Activity, R.layout.activity_genre)
    }

    @Provides
    fun provideActivityMovieByGenreBinding(@ActivityContext context: Context): ActivityMovieByGenreBinding {
        return DataBindingUtil.setContentView(context as Activity, R.layout.activity_movie_by_genre)
    }

    @Provides
    fun provideActivityMovieDetailBinding(@ActivityContext context: Context): ActivityMovieDetailBinding {
        return DataBindingUtil.setContentView(context as Activity, R.layout.activity_movie_detail)
    }

    @Provides
    fun provideActivityMovieReviewBinding(@ActivityContext context: Context): ActivityMovieReviewBinding {
        return DataBindingUtil.setContentView(context as Activity, R.layout.activity_movie_review)
    }
}
