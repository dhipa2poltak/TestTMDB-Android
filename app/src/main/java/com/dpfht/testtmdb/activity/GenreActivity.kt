package com.dpfht.testtmdb.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dpfht.testtmdb.TheApplication
import com.dpfht.testtmdb.adapter.GenreAdapter
import com.dpfht.testtmdb.databinding.ActivityGenreBinding
import com.dpfht.testtmdb.di.genreactivity.DaggerGenreActivityComponent
import com.dpfht.testtmdb.di.genreactivity.GenreActivityModule
import javax.inject.Inject

class GenreActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: GenreViewModel

    @Inject
    lateinit var adapter: GenreAdapter

    @Inject
    lateinit var binding: ActivityGenreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val genreActivityComponent = DaggerGenreActivityComponent
                .builder()
            .genreActivityModule(GenreActivityModule(this))
            .applicationComponent(TheApplication.get(this).applicationComponent)
            .build()

        genreActivityComponent.inject(this)

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
