package com.dpfht.testtmdb.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.dpfht.testtmdb.databinding.ActivityMovieDetailBinding
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailActivity : BaseActivity() {

    companion object {
        const val KEY_EXTRA_MOVIE_ID = "keyExtraMovieId"
    }

    private val viewModel: MovieDetailViewModel by viewModel()

    lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = get { parametersOf(this, viewModel) }

        viewModel.isShowDialogLoading.observe(this, { value ->
            if (value) {
                prgDialog.show()
            } else {
                prgDialog.dismiss()
            }
        })

        viewModel.toastMessage.observe(this, { value ->
            if (value != null && value.isNotEmpty()) {
                Toast.makeText(this@MovieDetailActivity, value, Toast.LENGTH_SHORT).show()
            }
        })

        if (intent.hasExtra(KEY_EXTRA_MOVIE_ID)) {

            val movieId = intent.getIntExtra(KEY_EXTRA_MOVIE_ID, -1)

            if (movieId != -1) {
                viewModel.doGetMovieDetail(movieId)
            }
        }
    }

    fun onClickShowReview() {
        if (viewModel.id != -1) {
            val itn = Intent(this@MovieDetailActivity, MovieReviewActivity::class.java)
            itn.putExtra(MovieReviewActivity.KEY_EXTRA_MOVIE_ID, viewModel.id)
            itn.putExtra(MovieReviewActivity.KEY_EXTRA_MOVIE_TITLE, viewModel.title.get())
            startActivity(itn)
        }
    }

    fun onClickShowTrailer() {
        if (viewModel.id != -1) {
            val itn = Intent(this@MovieDetailActivity, MovieTrailerActivity::class.java)
            itn.putExtra(MovieTrailerActivity.KEY_EXTRA_MOVIE_ID, viewModel.id)
            startActivity(itn)
        }
    }
}
