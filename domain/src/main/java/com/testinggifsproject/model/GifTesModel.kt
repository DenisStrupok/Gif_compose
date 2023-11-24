package com.testinggifsproject.model

data class GifModelData(
    val data: GifTesModel
)

class GifTesModel(
    val id: String?,
    val slug: String?,
    val url: String?,
    val bitlyUrl: String?,
    val embedUrl: String?,
    val username: String?,
    val source: String?,
    val contentUrl: String?,
    val sourceTld: String?,
    val sourcePostUrl: String?,
    val title: String?,
    val altText: String?
)