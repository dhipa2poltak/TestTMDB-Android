package com.dpfht.testtmdb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Suppress("unused")
class ReviewResponse {
    var id = 0
    var page = 0
    var results: List<Review>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages = 0

    @SerializedName("total_results")
    @Expose
    var totalResults = 0
}