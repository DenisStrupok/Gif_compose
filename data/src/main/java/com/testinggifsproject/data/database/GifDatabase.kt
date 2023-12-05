package com.testinggifsproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testinggifsproject.data.response.GifTestResponse

@Database(
    entities = [GifTestResponse::class],
    version = 1
)
abstract class GifDatabase : RoomDatabase() {
    abstract val dao: GifDao
}