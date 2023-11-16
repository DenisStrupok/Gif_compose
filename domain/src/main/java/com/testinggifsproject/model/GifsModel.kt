package com.testinggifsproject.model

data class GifsModel(
    var data: MutableList<Gif>?,
    val paginationModel: PaginationModel?
)