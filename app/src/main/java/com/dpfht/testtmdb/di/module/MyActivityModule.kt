package com.dpfht.testtmdb.di.module

import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.*
import com.dpfht.testtmdb.adapter.GenreAdapter
import com.dpfht.testtmdb.adapter.MovieByGenreAdapter
import com.dpfht.testtmdb.adapter.ReviewAdapter
import com.dpfht.testtmdb.databinding.ActivityGenreBinding
import com.dpfht.testtmdb.databinding.ActivityMovieByGenreBinding
import com.dpfht.testtmdb.databinding.ActivityMovieDetailBinding
import com.dpfht.testtmdb.databinding.ActivityMovieReviewBinding
import org.koin.dsl.module

val myActivityModule = module {

    factory { provideGenreAdapter(it[0]) }
    factory { provideActivityGenreBinding(it[0], it[1]) }

    factory { provideMovieByGenreAdapter(it[0]) }
    factory { provideActivityMovieByGenreBinding(it[0], it[1]) }

    factory { provideActivityMovieDetailBinding(it[0], it[1]) }

    factory { provideReviewAdapter(it[0]) }
    factory { provideActivityMovieReviewBinding(it[0], it[1]) }

    factory { provideLoadingDialog(it[0]) }
}

fun provideGenreAdapter(viewModel: GenreViewModel): GenreAdapter {
    return GenreAdapter(viewModel)
}

fun provideActivityGenreBinding(activity: GenreActivity, viewModel: GenreViewModel): ActivityGenreBinding {
    val binding = DataBindingUtil.setContentView<ActivityGenreBinding>(activity, R.layout.activity_genre)
    binding.viewModel = viewModel
    binding.activity = activity
    binding.lifecycleOwner = activity
    binding.executePendingBindings()

    return binding
}

fun provideMovieByGenreAdapter(viewModel: MovieByGenreViewModel): MovieByGenreAdapter {
    return MovieByGenreAdapter(viewModel)
}

fun provideActivityMovieByGenreBinding(activity: MovieByGenreActivity, viewModel: MovieByGenreViewModel): ActivityMovieByGenreBinding {
    val binding = DataBindingUtil.setContentView<ActivityMovieByGenreBinding>(activity, R.layout.activity_movie_by_genre)
    binding.viewModel = viewModel
    binding.activity = activity
    binding.lifecycleOwner = activity
    binding.executePendingBindings()

    return binding
}

fun provideActivityMovieDetailBinding(activity: MovieDetailActivity, viewModel: MovieDetailViewModel): ActivityMovieDetailBinding {
    val binding = DataBindingUtil.setContentView<ActivityMovieDetailBinding>(activity, R.layout.activity_movie_detail)
    binding.viewModel = viewModel
    binding.activity = activity
    binding.lifecycleOwner = activity
    binding.executePendingBindings()

    return binding
}

fun provideReviewAdapter(viewModel: MovieReviewViewModel): ReviewAdapter {
    return ReviewAdapter(viewModel)
}

fun provideActivityMovieReviewBinding(activity: MovieReviewActivity, viewModel: MovieReviewViewModel): ActivityMovieReviewBinding {
    val binding = DataBindingUtil.setContentView<ActivityMovieReviewBinding>(activity, R.layout.activity_movie_review)
    binding.viewModel = viewModel
    binding.activity = activity
    binding.lifecycleOwner = activity
    binding.executePendingBindings()

    return binding
}

fun provideLoadingDialog(activity: BaseActivity): AlertDialog {
    return AlertDialog.Builder(activity)
        .setCancelable(false)
        .setView(R.layout.dialog_loading)

        .create()
}
