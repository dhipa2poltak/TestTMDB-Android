package com.dpfht.testtmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.testtmdb.BR
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.GenreViewModel
import com.dpfht.testtmdb.databinding.RowGenreBinding

class GenreAdapter(private val viewModel: GenreViewModel): RecyclerView.Adapter<GenreAdapter.GenreHolder>() {

    private lateinit var binding: RowGenreBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_genre, parent, false)

        return GenreHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModel.genres.size
    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.bindData(viewModel, position)
    }

    inner class GenreHolder(private val binding: RowGenreBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(viewModel: GenreViewModel, position: Int) {
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
        }
    }
}