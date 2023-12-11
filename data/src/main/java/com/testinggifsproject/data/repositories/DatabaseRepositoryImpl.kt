package com.testinggifsproject.data.repositories

import com.testinggifsproject.data.database.GifDatabase
import com.testinggifsproject.data.response.GifTestResponse
import com.testinggifsproject.model.GifTesModel
import com.testinggifsproject.repositories.DatabaseRepository

class DatabaseRepositoryImpl(
    private val database: GifDatabase
) : DatabaseRepository {
    override suspend fun removeGifById(id: String): List<GifTesModel> {
        val result = database.dao.removeGifById(id = id)
        return if (result > 0) {
            try {
                database.dao.getAllGifs().map { GifTestResponse.mapToDomain(it) }
            } catch (e: Exception) {
                throw e
            }
        } else {
            emptyList()
        }
    }

    override suspend fun clearHistory(): List<GifTesModel> {
        val result = database.dao.removeAllGifs()
        return if (result > 0) {
            try {
                database.dao.getAllGifs().map { GifTestResponse.mapToDomain(it) }
            } catch (e: Exception) {
                throw e
            }
        } else {
            emptyList()
        }
    }
}