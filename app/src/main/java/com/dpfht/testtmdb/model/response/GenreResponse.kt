package com.dpfht.testtmdb.model.response

import com.dpfht.testtmdb.model.Genre

data class GenreResponse(
    var genres: List<Genre>? = null
)
