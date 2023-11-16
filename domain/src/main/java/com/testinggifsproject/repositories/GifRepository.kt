package com.testinggifsproject.repositories

import com.testinggifsproject.model.GifsModel

interface GifRepository {
    suspend fun getRandomListGifs(limit: Int, offset: Int): GifsModel

}