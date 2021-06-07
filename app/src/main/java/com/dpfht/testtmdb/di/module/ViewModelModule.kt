package com.dpfht.testtmdb.di.module

import com.dpfht.testtmdb.activity.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        GenreViewModel(get())
    }

    viewModel {
        MovieByGenreViewModel(get())
    }

    viewModel {
        MovieDetailViewModel(get())
    }

    viewModel {
        MovieReviewViewModel(get())
    }

    viewModel {
        MovieTrailerViewModel(get())
    }
}
