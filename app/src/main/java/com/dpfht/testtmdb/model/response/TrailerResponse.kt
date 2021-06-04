package com.dpfht.testtmdb.model.response

import com.dpfht.testtmdb.model.Trailer

data class TrailerResponse(
    var id: Int = 0,
    var results: ArrayList<Trailer>? = null
)
