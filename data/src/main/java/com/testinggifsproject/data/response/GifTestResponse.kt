package com.testinggifsproject.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.testinggifsproject.model.GifModelData
import com.testinggifsproject.model.GifTesModel
import com.testinggifsproject.model.ModelMapper

data class GifResponseData(
    @SerializedName("data")
    val data: GifTestResponse
) {
    companion object : ModelMapper<GifModelData, GifResponseData> {
        override fun mapTo(model: GifModelData): GifResponseData {
            return with(model) {
                GifResponseData(
                    data = GifTestResponse.mapTo(data)
                )
            }
        }

        override fun mapToDomain(model: GifResponseData): GifModelData {
            return with(model) {
                GifModelData(
                    data = GifTestResponse.mapToDomain(data)
                )
            }
        }
    }
}

@Entity(tableName = "gif_table")
data class GifTestResponse(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("bitly_url")
    val bitlyUrl: String?,
    @SerializedName("embed_url")
    val embedUrl: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("content_url")
    val contentUrl: String?,
    val sourceTld: String?,
    val sourcePostUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("alt_text")
    val altText: String?
) {
    companion object : ModelMapper<GifTesModel, GifTestResponse> {
        override fun mapTo(model: GifTesModel): GifTestResponse {
            return with(model) {
                GifTestResponse(
                    id = id ?: "",
                    slug = slug,
                    url = url,
                    bitlyUrl = bitlyUrl,
                    embedUrl = embedUrl,
                    username = username,
                    source = source,
                    contentUrl = contentUrl,
                    sourceTld = sourceTld,
                    sourcePostUrl = sourcePostUrl,
                    title = title,
                    altText = altText
                )
            }
        }

        override fun mapToDomain(model: GifTestResponse): GifTesModel {
            return with(model) {
                GifTesModel(
                    id = id,
                    slug = slug,
                    url = url,
                    bitlyUrl = bitlyUrl,
                    embedUrl = embedUrl,
                    username = username,
                    source = source,
                    contentUrl = contentUrl,
                    sourceTld = sourceTld,
                    sourcePostUrl = sourcePostUrl,
                    title = title,
                    altText = altText
                )
            }
        }
    }
}
