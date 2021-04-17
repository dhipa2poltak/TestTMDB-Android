package com.dpfht.testtmdb.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.dpfht.testtmdb.adapter.GenreAdapter
import com.dpfht.testtmdb.databinding.ActivityGenreBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GenreActivity : BaseActivity() {

    private val viewModel: GenreViewModel by viewModels()

    @Inject
    lateinit var adapter: GenreAdapter

    @Inject
    lateinit var binding: ActivityGenreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter.viewModel = viewModel

        binding.viewModel = viewModel
        binding.activity = this
        binding.executePendingBindings()

        viewModel.genreData.observe(this, {
            adapter.notifyDataSetChanged()
        })

        viewModel.movieByGenreActivity.observe(this, { value ->
            if (value != null) {
                val itn = Intent(this, value.first)
                if (value.second != null) {
                    itn.putExtra(MovieByGenreActivity.KEY_EXTRA_GENRE_ID, value.second?.id)
                    itn.putExtra(MovieByGenreActivity.KEY_EXTRA_GENRE_NAME, value.second?.name)
                }
                startActivity(itn)
                viewModel.movieByGenreActivity.value = null
            }
        })

        viewModel.isShowDialogLoading.observe(this, { value ->
            if (value) {
                prgDialog.show()
            } else {
                prgDialog.dismiss()
            }
        })

        viewModel.toastMessage.observe(this, { value ->
            if (value != null && value.isNotEmpty()) {
                Toast.makeText(this@GenreActivity, value, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.doGetMovieGenre()
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.myCompositeDisposable.clear()
    }
}
