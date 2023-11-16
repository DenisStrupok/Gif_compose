package com.testinggifsproject.data.repositories

import com.testinggifsproject.data.response.ListGifResponse
import com.testinggifsproject.data.service.GifService
import com.testinggifsproject.model.GifsModel
import com.testinggifsproject.repositories.GifRepository

class GifRepositoryImpl(
    private val gifService: GifService
): GifRepository {
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

}