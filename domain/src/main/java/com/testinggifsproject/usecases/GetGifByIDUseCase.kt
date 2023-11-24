package com.testinggifsproject.usecases

import com.testinggifsproject.model.Gif
import com.testinggifsproject.model.GifModelData
import com.testinggifsproject.model.GifTesModel
import com.testinggifsproject.repositories.GifRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetGifByIDUseCase(
   private val  gifRepository: GifRepository
): BaseUseCase<GetGifByIDUseCase.Params, GifModelData>() {


    override suspend fun remoteWork(params: Params?): GifModelData {
        return withContext(Dispatchers.IO){
            gifRepository.getGifById(
                id = params!!.gifId
            )
        }
    }
    class Params(
        val gifId: String
    )
}