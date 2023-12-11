package com.testinggifsproject.usecases.database

import com.testinggifsproject.model.GifTesModel
import com.testinggifsproject.repositories.DatabaseRepository
import com.testinggifsproject.usecases.BaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClearHistoryUseCase(
    private val databaseRepository: DatabaseRepository
) : BaseUseCase<Unit, List<GifTesModel>>() {
    override suspend fun remoteWork(params: Unit?): List<GifTesModel> {
        return withContext(Dispatchers.IO) {
            databaseRepository.clearHistory()
        }
    }
}