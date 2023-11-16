package com.testinggifsproject.usecases

import com.testinggifsproject.model.GifsModel
import com.testinggifsproject.repositories.GifRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRandomListGifsUseCase(
    private val gifRepository: GifRepository
) : BaseUseCase<GetRandomListGifsUseCase.Params, GifsModel>() {

    override suspend fun remoteWork(params: Params?): GifsModel {
        return withContext(Dispatchers.IO) {
            gifRepository.getRandomListGifs(
                limit = params!!.limit,
                offset = params.offset
            )
        }
    }

    class Params(
        val limit: Int,
        val offset: Int
    )
}