package com.testinggifsproject.data.service

import com.testinggifsproject.data.response.ListGifResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {

    @GET("gifs/trending")
    suspend fun getRandomListGifsList(
        @Query("api_key") key: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ListGifResponse
}