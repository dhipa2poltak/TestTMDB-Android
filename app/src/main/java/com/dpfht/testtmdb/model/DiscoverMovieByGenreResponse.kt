package com.dpfht.testtmdb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Suppress("unused")
class DiscoverMovieByGenreResponse {
    var page = 0
    var results: List<Movie>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages = 0

    @SerializedName("total_results")
    @Expose
    var totalResults = 0
}