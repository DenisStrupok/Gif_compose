package com.testinggifsproject.usecases.database

import com.testinggifsproject.model.GifTesModel
import com.testinggifsproject.repositories.GifRepository
import com.testinggifsproject.usecases.BaseUseCase
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class GetHistoryUseCase(
    private val gifRepository: GifRepository
) : BaseUseCase<Unit, List<GifTesModel>>() {

    override suspend fun remoteWork(params: Unit?): List<GifTesModel> {
        return withContext(Dispatchers.IO) {
            gifRepository.getHistory()
        }
    }

}