package com.dpfht.testtmdb.di.movietraileractivity

import androidx.databinding.DataBindingUtil
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.MovieTrailerActivity
import com.dpfht.testtmdb.activity.MovieTrailerViewModel
import com.dpfht.testtmdb.databinding.ActivityMovieTrailerBinding
import com.dpfht.testtmdb.repository.AppRepository
import dagger.Module
import dagger.Provides

@Module
class MovieTrailerActivityModule(private val movieTrailerActivity: MovieTrailerActivity) {

    @Provides
    @MovieTrailerActivityScope
    fun provideMovieTrailerViewModel(appRepository: AppRepository): MovieTrailerViewModel {
        val viewModel = MovieTrailerViewModel()
        viewModel.appRepository = appRepository

        return viewModel
    }

    @Provides
    @MovieTrailerActivityScope
    fun provideActivityMovieTrailerBinding(): ActivityMovieTrailerBinding {
        val binding = DataBindingUtil.setContentView<ActivityMovieTrailerBinding>(movieTrailerActivity, R.layout.activity_movie_trailer)
        //binding.lifecycleOwner = movieTrailerActivity
        binding.executePendingBindings()

        return binding
    }
}
