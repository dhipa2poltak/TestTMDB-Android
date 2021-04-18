package com.dpfht.testtmdb.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.testtmdb.adapter.ReviewAdapter
import com.dpfht.testtmdb.databinding.ActivityMovieReviewBinding
import kotlinx.android.synthetic.main.activity_movie_review.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieReviewActivity : BaseActivity() {

    companion object {
        const val KEY_EXTRA_MOVIE_ID = "keyExtraMovieId"
        const val KEY_EXTRA_MOVIE_TITLE = "keyExtraMovieTitle"
    }

    private val viewModel: MovieReviewViewModel by viewModel()

    val adapter: ReviewAdapter by inject { parametersOf(viewModel) }
    lateinit var binding: ActivityMovieReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = get { parametersOf(this, viewModel) }

        rvReview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val xx = recyclerView.computeVerticalScrollRange()
                val xy = recyclerView.computeVerticalScrollOffset()
                val xz = recyclerView.computeVerticalScrollExtent()
                val zz = (xy.toFloat() / (xx - xz).toFloat() * 100).toInt()
                if (zz >= 75 && !viewModel.isLoadingData) {
                    viewModel.doGetMovieReviews(viewModel.movieId, viewModel.page + 1)
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        viewModel.reviewData.observe(this, Observer {
            adapter.notifyItemInserted(viewModel.reviews.size - 1)
        })

        viewModel.toastMessage.observe(this, Observer { value ->
            if (value != null && value.isNotEmpty()) {
                Toast.makeText(this@MovieReviewActivity, value, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.isShowDialogLoading.observe(this, Observer { value ->
            if (value) {
                if (viewModel.reviews.isEmpty()) {
                    prgDialog.show()
                }
            } else {
                prgDialog.dismiss()
            }
        })

        if (intent.hasExtra(KEY_EXTRA_MOVIE_ID)) {

            val movieTitle = intent.getStringExtra(KEY_EXTRA_MOVIE_TITLE)
            if (movieTitle != null) {
                tvMovieName.text = movieTitle
            }

            val movieId = intent.getIntExtra(KEY_EXTRA_MOVIE_ID, -1)
            if (movieId != -1 && viewModel.reviews.size == 0) {
                viewModel.movieId = movieId
                viewModel.doGetMovieReviews(movieId, viewModel.page)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.myCompositeDisposable.clear()
    }
}
