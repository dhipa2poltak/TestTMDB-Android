package com.dpfht.testtmdb.di.module

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.GenreActivity
import com.dpfht.testtmdb.activity.MovieByGenreActivity
import com.dpfht.testtmdb.activity.MovieDetailActivity
import com.dpfht.testtmdb.activity.MovieReviewActivity
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
        val binding = DataBindingUtil.setContentView<ActivityGenreBinding>(context as Activity, R.layout.activity_genre)
        binding.lifecycleOwner = context as GenreActivity
        return binding
    }

    @Provides
    fun provideActivityMovieByGenreBinding(@ActivityContext context: Context): ActivityMovieByGenreBinding {
        val binding = DataBindingUtil.setContentView<ActivityMovieByGenreBinding>(context as Activity, R.layout.activity_movie_by_genre)
        binding.lifecycleOwner = context as MovieByGenreActivity
        return binding
    }

    @Provides
    fun provideActivityMovieDetailBinding(@ActivityContext context: Context): ActivityMovieDetailBinding {
        val binding = DataBindingUtil.setContentView<ActivityMovieDetailBinding>(context as Activity, R.layout.activity_movie_detail)
        binding.lifecycleOwner = context as MovieDetailActivity
        return binding
    }

    @Provides
    fun provideActivityMovieReviewBinding(@ActivityContext context: Context): ActivityMovieReviewBinding {
        val binding = DataBindingUtil.setContentView<ActivityMovieReviewBinding>(context as Activity, R.layout.activity_movie_review)
        binding.lifecycleOwner = context as MovieReviewActivity
        return binding
    }

    @Provides
    fun provideLoadingDialog(@ActivityContext context: Context): AlertDialog {
        return AlertDialog.Builder(context)
            .setCancelable(false)
            .setView(R.layout.dialog_loading)
            .create()
    }
}
