package com.dpfht.testtmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.testtmdb.BR
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.MovieByGenreViewModel
import com.dpfht.testtmdb.databinding.RowMovieBinding
import javax.inject.Inject

class MovieByGenreAdapter @Inject constructor(): RecyclerView.Adapter<MovieByGenreAdapter.MovieByGenreHolder>() {

    var viewModel: MovieByGenreViewModel? = null
    private lateinit var binding: RowMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieByGenreAdapter.MovieByGenreHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_movie, parent, false)

        return MovieByGenreHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModel?.movies?.size ?: 0
    }

    override fun onBindViewHolder(holder: MovieByGenreAdapter.MovieByGenreHolder, position: Int) {
        if (viewModel != null) {
            holder.bindData(viewModel!!, position)
        }
    }

    inner class MovieByGenreHolder(private val binding: RowMovieBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(viewModel: MovieByGenreViewModel, position: Int) {
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
        }
    }
}
