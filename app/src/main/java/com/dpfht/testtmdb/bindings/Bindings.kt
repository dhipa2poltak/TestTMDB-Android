package com.dpfht.testtmdb.bindings

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dpfht.testtmdb.R

object Bindings {
    @BindingAdapter("setAdapter")
    @JvmStatic
    fun bindRecyclerViewAdapter(
            recyclerView: RecyclerView,
            adapter: RecyclerView.Adapter<*>?
    ) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

    @BindingAdapter("toastMessage")
    @JvmStatic
    fun showToast(view: View, message: String?) {
        if (message != null && message.isNotEmpty()) {
            Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun showImageByUri(iv: ImageView, imageUrl: String?) {
        if (imageUrl != null && imageUrl.isNotEmpty()) {
            Glide.with(iv.context)
                    .load(imageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv)
        }
    }

    @BindingAdapter("imageAvatarUrl")
    @JvmStatic
    fun showImageAvatarByUrl(iv: ImageView, imageAvatarUrl: String?) {
        if (imageAvatarUrl != null) {
            var avatarPath = imageAvatarUrl
            if (avatarPath.startsWith("/")) {
                avatarPath = avatarPath.replaceFirst("/", "")
            }

            Glide.with(iv.context)
                    .load(avatarPath)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv)
        }
    }
}
