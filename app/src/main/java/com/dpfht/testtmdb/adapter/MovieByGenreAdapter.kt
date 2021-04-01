package com.dpfht.testtmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.testtmdb.BR
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.MovieByGenreViewModel
import com.dpfht.testtmdb.databinding.RowMovieBinding

class MovieByGenreAdapter(private val viewModel: MovieByGenreViewModel): RecyclerView.Adapter<MovieByGenreAdapter.MovieByGenreHolder>() {

    private lateinit var binding: RowMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieByGenreAdapter.MovieByGenreHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_movie, parent, false)

        return MovieByGenreHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModel.movies.size
    }

    override fun onBindViewHolder(holder: MovieByGenreAdapter.MovieByGenreHolder, position: Int) {
        holder.bindData(viewModel, position)
    }

    inner class MovieByGenreHolder(private val binding: RowMovieBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(viewModel: MovieByGenreViewModel, position: Int) {
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
        }
    }
}
