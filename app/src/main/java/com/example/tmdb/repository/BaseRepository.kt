package com.example.tmdb.repository

import com.example.tmdb.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Flow<NetworkResult<T>> =
        flow {
            emit(NetworkResult.Loading)

            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful && response.body() != null) {
                    emit(NetworkResult.Success(response.body()!!))
                } else {
                    response.errorBody()?.let {
                        it.close()
                        emit(NetworkResult.Failure(it.toString()))
                    }
                }
            } catch (e: HttpException) {
                emit(NetworkResult.Failure(e.message))
            } catch (e: Exception) {
                emit(NetworkResult.Failure(e.localizedMessage))
            }
        }.flowOn(Dispatchers.IO)
}