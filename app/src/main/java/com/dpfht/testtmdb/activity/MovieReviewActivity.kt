package com.dpfht.testtmdb.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.adapter.ReviewAdapter
import com.dpfht.testtmdb.databinding.ActivityMovieReviewBinding
import com.dpfht.testtmdb.rest.RestClient
import com.dpfht.testtmdb.rest.RestService
import kotlinx.android.synthetic.main.activity_movie_review.*

class MovieReviewActivity : BaseActivity() {

    companion object {
        const val KEY_EXTRA_MOVIE_ID = "keyExtraMovieId"
        const val KEY_EXTRA_MOVIE_TITLE = "keyExtraMovieTitle"
    }

    lateinit var viewModel: MovieReviewViewModel
    lateinit var adapter: ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[MovieReviewViewModel::class.java]
        viewModel.restApi = RestClient.client?.create(RestService::class.java)
        adapter = ReviewAdapter(viewModel)

        val binding = DataBindingUtil.setContentView<ActivityMovieReviewBinding>(this, R.layout.activity_movie_review)
        binding.viewModel = viewModel
        binding.activity = this
        binding.executePendingBindings()

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
            adapter.notifyDataSetChanged()
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
            if (movieId != -1) {
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
