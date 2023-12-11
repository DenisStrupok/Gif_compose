package com.testinggifsproject.data.repositories

import com.testinggifsproject.data.database.GifDatabase
import com.testinggifsproject.data.response.GifResponseData
import com.testinggifsproject.data.response.GifTestResponse
import com.testinggifsproject.data.response.ListGifResponse
import com.testinggifsproject.data.service.GifService
import com.testinggifsproject.model.GifModelData
import com.testinggifsproject.model.GifTesModel
import com.testinggifsproject.model.GifsModel
import com.testinggifsproject.repositories.GifRepository

class GifRepositoryImpl(
    private val gifService: GifService,
    private val database: GifDatabase
) : GifRepository {
    override suspend fun getRandomListGifs(limit: Int, offset: Int): GifsModel {
        return try {
            ListGifResponse.mapToDomain(
                gifService.getRandomListGifsList(
                    key = "lmLp5f9xrCaT8dRrDiAixYt16ntHnI2M",
                    limit = limit,
                    offset = offset
                )
            )
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun findGifsByName(name: String, offset: Int, limit: Int): GifsModel {
        return try {
            ListGifResponse.mapToDomain(
                gifService.findGifsByName(
                    name = name,
                    key = "lmLp5f9xrCaT8dRrDiAixYt16ntHnI2M",
                    limit = limit,
                    offset = offset
                )
            )
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getGifById(id: String): GifModelData {
        val gifResult = gifService.getGifById(
            id = id,
            key = "lmLp5f9xrCaT8dRrDiAixYt16ntHnI2M"
        )
        database.dao.insertGif(gifResult.data)
        return try {
            GifResponseData.mapToDomain(gifResult)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getHistory(): List<GifTesModel> {
        return try {
            database.dao.getAllGifs().map { GifTestResponse.mapToDomain(it) }
        } catch (e: Exception) {
            throw e
        }
    }
}