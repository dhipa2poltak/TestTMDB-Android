package com.dpfht.testtmdb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Suppress("unused")
class Movie {
    var adult = false

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null

    var id = 0

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    var overview: String? = null
    var popularity = 0.0

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    var title: String? = null
    var video = false

    @SerializedName("vote_average")
    @Expose
    var voteAverage = 0.0

    @SerializedName("vote_count")
    @Expose
    var voteCount = 0
}
