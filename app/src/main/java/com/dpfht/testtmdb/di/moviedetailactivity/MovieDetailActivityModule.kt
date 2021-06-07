package com.dpfht.testtmdb.di.moviedetailactivity

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.MovieDetailActivity
import com.dpfht.testtmdb.activity.MovieDetailViewModel
import com.dpfht.testtmdb.databinding.ActivityMovieDetailBinding
import com.dpfht.testtmdb.di.ActivityContext
import com.dpfht.testtmdb.di.ActivityModule
import com.dpfht.testtmdb.repository.AppRepository
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityModule::class])
class MovieDetailActivityModule(private val movieDetailActivity: MovieDetailActivity) {

    @Provides
    @MovieDetailActivityScope
    @ActivityContext
    fun getContext(): Context {
        return movieDetailActivity
    }

    @Provides
    @MovieDetailActivityScope
    fun provideMovieDetailViewModel(appRepository: AppRepository): MovieDetailViewModel {
        val viewModel = ViewModelProvider(movieDetailActivity)[MovieDetailViewModel::class.java]
        viewModel.appRepository = appRepository
        return viewModel
    }

    @Provides
    @MovieDetailActivityScope
    fun provideActivityMovieDetailBinding(viewModel: MovieDetailViewModel): ActivityMovieDetailBinding {
        val binding = DataBindingUtil.setContentView<ActivityMovieDetailBinding>(movieDetailActivity, R.layout.activity_movie_detail)
        binding.viewModel = viewModel
        binding.activity = movieDetailActivity
        binding.lifecycleOwner = movieDetailActivity
        binding.executePendingBindings()

        return binding
    }
}
