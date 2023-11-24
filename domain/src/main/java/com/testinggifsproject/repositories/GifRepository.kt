package com.testinggifsproject.repositories

import com.testinggifsproject.model.Gif
import com.testinggifsproject.model.GifModelData
import com.testinggifsproject.model.GifTesModel
import com.testinggifsproject.model.GifsModel

interface GifRepository {
    suspend fun getRandomListGifs(limit: Int, offset: Int): GifsModel

    suspend fun findGifsByName(name: String, offset: Int, limit: Int): GifsModel

    suspend fun getGifById(id: String): GifModelData
}