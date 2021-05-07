package com.dpfht.testtmdb.di.moviebygenreactivity

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.MovieByGenreActivity
import com.dpfht.testtmdb.activity.MovieByGenreViewModel
import com.dpfht.testtmdb.adapter.MovieByGenreAdapter
import com.dpfht.testtmdb.databinding.ActivityMovieByGenreBinding
import com.dpfht.testtmdb.di.ActivityContext
import com.dpfht.testtmdb.di.ActivityModule
import com.dpfht.testtmdb.rest.RestService
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityModule::class])
class MovieByGenreActivityModule(private val movieByGenreActivity: MovieByGenreActivity) {

    @Provides
    @MovieByGenreActivityScope
    @ActivityContext
    fun getContext(): Context {
        return movieByGenreActivity
    }

    @Provides
    @MovieByGenreActivityScope
    fun provideMovieByGenreViewModel(restApi: RestService): MovieByGenreViewModel {
        val viewModel = ViewModelProvider(movieByGenreActivity)[MovieByGenreViewModel::class.java]
        viewModel.restApi = restApi
        return viewModel
    }

    @Provides
    @MovieByGenreActivityScope
    fun provideMovieByGenreAdapter(viewModel: MovieByGenreViewModel): MovieByGenreAdapter {
        return MovieByGenreAdapter(viewModel)
    }

    @Provides
    @MovieByGenreActivityScope
    fun provideActivityMovieByGenreBinding(viewModel: MovieByGenreViewModel): ActivityMovieByGenreBinding {
        val binding = DataBindingUtil.setContentView<ActivityMovieByGenreBinding>(
            movieByGenreActivity,
            R.layout.activity_movie_by_genre
        )
        binding.viewModel = viewModel
        binding.activity = movieByGenreActivity
        binding.lifecycleOwner = movieByGenreActivity
        binding.executePendingBindings()

        return binding
    }
}