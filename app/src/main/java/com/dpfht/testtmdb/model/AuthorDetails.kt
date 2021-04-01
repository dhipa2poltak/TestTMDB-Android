package com.dpfht.testtmdb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Suppress("unused")
class AuthorDetails {
    var name: String? = null
    var username: String? = null

    @SerializedName("avatar_path")
    @Expose
    var avatarPath: String? = null

    var rating = 0.0
}
