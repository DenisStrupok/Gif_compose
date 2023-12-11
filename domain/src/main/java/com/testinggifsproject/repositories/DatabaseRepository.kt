package com.testinggifsproject.repositories

import com.testinggifsproject.model.GifTesModel

interface DatabaseRepository {

    suspend fun removeGifById(id: String): List<GifTesModel>

    suspend fun clearHistory(): List<GifTesModel>
}