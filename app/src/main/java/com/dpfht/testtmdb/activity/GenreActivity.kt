package com.dpfht.testtmdb.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dpfht.testtmdb.adapter.GenreAdapter
import com.dpfht.testtmdb.databinding.ActivityGenreBinding
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GenreActivity : BaseActivity() {

    private val viewModel: GenreViewModel by viewModel()

    val adapter: GenreAdapter by inject { parametersOf(viewModel) }
    lateinit var binding: ActivityGenreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = get { parametersOf(this, viewModel) }

        viewModel.genreData.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })

        viewModel.movieByGenreActivity.observe(this, Observer { value ->
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

        viewModel.isShowDialogLoading.observe(this, Observer { value ->
            if (value) {
                prgDialog.show()
            } else {
                prgDialog.dismiss()
            }
        })

        viewModel.toastMessage.observe(this, Observer { value ->
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
