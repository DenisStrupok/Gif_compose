package com.testinggifsproject.usecases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCase<PARAMS, RESULT> : UseCase<RESULT, PARAMS> {
    operator fun invoke(
        uiDispatcher: CoroutineScope,
        result: ResultCallbacks<RESULT>,
        params: PARAMS
    ): Job {
        return uiDispatcher.launch {
            withContext(Dispatchers.Main) {
                result.onLoading?.invoke(true)
                try {
                    val resultOfWork = remoteWork(params)
                    result.onSuccess?.invoke(resultOfWork)
                    result.onLoading?.invoke(false)
                } catch (e: Throwable) {
                    result.onLoading?.invoke(false)
                }
            }
        }
    }

}

class ResultCallbacks<T>(
    val onSuccess: ((T) -> Unit)? = null,
    val onLoading: ((Boolean) -> Unit)? = null,
)


interface UseCase<RESULT, PARAMS> {
    suspend fun remoteWork(params: PARAMS?): RESULT

}