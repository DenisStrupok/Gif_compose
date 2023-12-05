package com.testinggifsproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testinggifsproject.data.response.GifTestResponse

@Dao
interface GifDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGif(gif: GifTestResponse)

    @Query("SELECT * FROM gif_table")
    suspend fun getAllGifs(): List<GifTestResponse>
}