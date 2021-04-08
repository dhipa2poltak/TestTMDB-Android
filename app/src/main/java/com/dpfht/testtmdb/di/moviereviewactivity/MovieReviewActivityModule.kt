package com.dpfht.testtmdb.di.moviereviewactivity

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.MovieReviewActivity
import com.dpfht.testtmdb.activity.MovieReviewViewModel
import com.dpfht.testtmdb.adapter.ReviewAdapter
import com.dpfht.testtmdb.databinding.ActivityMovieReviewBinding
import com.dpfht.testtmdb.di.ActivityContext
import com.dpfht.testtmdb.di.ActivityModule
import com.dpfht.testtmdb.rest.RestService
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityModule::class])
class MovieReviewActivityModule(private val movieReviewActivity: MovieReviewActivity) {

    @Provides
    @MovieReviewActivityScope
    @ActivityContext
    fun getContext(): Context {
        return movieReviewActivity
    }

    @Provides
    @MovieReviewActivityScope
    fun provideMovieReviewViewModel(restApi: RestService): MovieReviewViewModel {
        val viewModel = ViewModelProvider(movieReviewActivity)[MovieReviewViewModel::class.java]
        viewModel.restApi = restApi
        return viewModel
    }

    @Provides
    @MovieReviewActivityScope
    fun provideReviewAdapter(viewModel: MovieReviewViewModel): ReviewAdapter {
        return ReviewAdapter(viewModel)
    }

    @Provides
    @MovieReviewActivityScope
    fun provideActivityMovieReviewBinding(viewModel: MovieReviewViewModel): ActivityMovieReviewBinding {
        val binding = DataBindingUtil.setContentView<ActivityMovieReviewBinding>(movieReviewActivity, R.layout.activity_movie_review)
        binding.viewModel = viewModel
        binding.activity = movieReviewActivity
        binding.executePendingBindings()

        return binding
    }
}
