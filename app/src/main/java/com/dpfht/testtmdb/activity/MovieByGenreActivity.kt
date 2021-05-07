package com.dpfht.testtmdb.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.testtmdb.adapter.MovieByGenreAdapter
import com.dpfht.testtmdb.databinding.ActivityMovieByGenreBinding
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieByGenreActivity : BaseActivity() {

    companion object {
        const val KEY_EXTRA_GENRE_ID = "keyExtraGenreId"
        const val KEY_EXTRA_GENRE_NAME = "keyExtraGenreName"
    }

    private val viewModel: MovieByGenreViewModel by viewModel()

    val adapter: MovieByGenreAdapter by inject { parametersOf(viewModel) }
    lateinit var binding: ActivityMovieByGenreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = get { parametersOf(this, viewModel) }

        binding.rvMovieByGenre.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val xx = recyclerView.computeVerticalScrollRange()
                val xy = recyclerView.computeVerticalScrollOffset()
                val xz = recyclerView.computeVerticalScrollExtent()
                val zz = (xy.toFloat() / (xx - xz).toFloat() * 100).toInt()
                if (zz >= 75 && !viewModel.isLoadingData) {
                    viewModel.doGetMoviesByGenre(viewModel.genreId.toString(), viewModel.page + 1)
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        viewModel.movieData.observe(this, {
            adapter.notifyItemInserted(viewModel.movies.size - 1)
        })

        viewModel.isShowDialogLoading.observe(this, { value ->
            if (value) {
                if (viewModel.movies.isEmpty()) {
                    prgDialog.show()
                }
            } else {
                prgDialog.dismiss()
            }
        })

        viewModel.movieDetailActivity.observe(this, { value ->
            //Toast.makeText(this@MovieByGenreActivity, "size: ${viewModel.movies.size}", Toast.LENGTH_SHORT).show()
            if (value != null) {
                val itn = Intent(this, value.first)
                if (value.second != null) {
                    itn.putExtra(MovieDetailActivity.KEY_EXTRA_MOVIE_ID, value.second?.id)
                }
                startActivity(itn)
                viewModel.movieDetailActivity.value = null
            }
        })

        viewModel.toastMessage.observe(this, { value ->
            if (value != null && value.isNotEmpty()) {
                Toast.makeText(this@MovieByGenreActivity, value, Toast.LENGTH_SHORT).show()
            }
        })

        if (intent.hasExtra(KEY_EXTRA_GENRE_ID)) {
            val genreName = intent.getStringExtra(KEY_EXTRA_GENRE_NAME)
            if (genreName != null) {
                val str = "Genre $genreName Movies"
                viewModel.title.value = str
            }

            val genreId = intent.getIntExtra(KEY_EXTRA_GENRE_ID, -1)
            if (genreId != -1 && viewModel.movies.size == 0) {
                viewModel.genreId = genreId
                viewModel.doGetMoviesByGenre(genreId.toString(), viewModel.page)
            }
        }

        //Toast.makeText(this, "size: ${viewModel.movies.size}", Toast.LENGTH_SHORT).show()
    }
}
