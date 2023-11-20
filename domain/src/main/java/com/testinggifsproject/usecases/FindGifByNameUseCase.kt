package com.testinggifsproject.usecases

import com.testinggifsproject.model.GifsModel
import com.testinggifsproject.repositories.GifRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FindGifByNameUseCase(
    private val gifRepository: GifRepository
) : BaseUseCase<FindGifByNameUseCase.Params, GifsModel>() {

    override suspend fun remoteWork(params: Params?): GifsModel {
        return withContext(Dispatchers.IO) {
            gifRepository.findGifsByName(
                name = params!!.name,
                offset = params.offset,
                limit = params.limit
            )
        }
    }
    class Params(
       val name: String,
       val limit: Int,
       val offset: Int
    )
}