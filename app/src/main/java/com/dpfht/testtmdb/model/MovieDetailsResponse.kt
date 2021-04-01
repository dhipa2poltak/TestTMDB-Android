package com.dpfht.testtmdb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Suppress("unused")
class MovieDetailsResponse {

    var adult = false

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("belongs_to_collection")
    @Expose
    var belongsToCollection: Any? = null

    var budget = 0
    var genres: List<Genre>? = null
    var homepage: String? = null
    var id = 0

    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null

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

    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompany>? = null

    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountry>? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    var revenue = 0
    var runtime = 0

    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguage>? = null

    var status: String? = null
    var tagline: String? = null
    var title: String? = null
    var video = false

    @SerializedName("vote_average")
    @Expose
    var voteAverage = 0.0

    @SerializedName("vote_count")
    @Expose
    var voteCount = 0
}
