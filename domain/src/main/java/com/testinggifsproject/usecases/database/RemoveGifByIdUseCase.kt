package com.testinggifsproject.usecases.database

import com.testinggifsproject.model.GifTesModel
import com.testinggifsproject.repositories.DatabaseRepository
import com.testinggifsproject.usecases.BaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoveGifByIdUseCase(
    private val databaseRepository: DatabaseRepository
) : BaseUseCase<RemoveGifByIdUseCase.Params, List<GifTesModel>>() {

    override suspend fun remoteWork(params: Params?): List<GifTesModel> {
        return withContext(Dispatchers.IO) {
            databaseRepository.removeGifById(
                id = params!!.gifId
            )
        }
    }

    class Params(
        val gifId: String
    )
}