package com.dpfht.testtmdb.di.genreactivity

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.GenreActivity
import com.dpfht.testtmdb.activity.GenreViewModel
import com.dpfht.testtmdb.adapter.GenreAdapter
import com.dpfht.testtmdb.databinding.ActivityGenreBinding
import com.dpfht.testtmdb.di.ActivityContext
import com.dpfht.testtmdb.di.ActivityModule
import com.dpfht.testtmdb.repository.AppRepository
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityModule::class])
class GenreActivityModule(private val genreActivity: GenreActivity) {

    @Provides
    @GenreActivityScope
    @ActivityContext
    fun getContext(): Context {
        return genreActivity
    }

    @Provides
    @GenreActivityScope
    fun provideGenreViewModel(appRepository: AppRepository): GenreViewModel {
        val viewModel = ViewModelProvider(genreActivity)[GenreViewModel::class.java]
        viewModel.appRepository = appRepository
        return viewModel
    }

    @Provides
    @GenreActivityScope
    fun provideGenreAdapter(viewModel: GenreViewModel): GenreAdapter {
        return GenreAdapter(viewModel)
    }

    @Provides
    @GenreActivityScope
    fun provideActivityGenreBinding(viewModel: GenreViewModel): ActivityGenreBinding {
        val binding = DataBindingUtil.setContentView<ActivityGenreBinding>(
            genreActivity,
            R.layout.activity_genre
        )
        binding.viewModel = viewModel
        binding.activity = genreActivity
        binding.lifecycleOwner = genreActivity
        binding.executePendingBindings()

        return binding
    }
}
