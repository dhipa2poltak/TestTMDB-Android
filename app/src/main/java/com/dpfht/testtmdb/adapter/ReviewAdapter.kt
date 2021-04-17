package com.dpfht.testtmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.testtmdb.BR
import com.dpfht.testtmdb.R
import com.dpfht.testtmdb.activity.MovieReviewViewModel
import com.dpfht.testtmdb.databinding.RowReviewBinding
import javax.inject.Inject

class ReviewAdapter @Inject constructor(): RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {

    var viewModel: MovieReviewViewModel? = null

    private lateinit var binding: RowReviewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_review, parent, false)

        return ReviewHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModel?.reviews?.size ?: 0
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        if (viewModel != null) {
            holder.bindData(viewModel!!, position)
        }
    }

    inner class ReviewHolder(private val binding: RowReviewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(viewModel: MovieReviewViewModel, position: Int) {
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }
    }
}
