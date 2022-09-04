package com.example.tmdb.repository

import com.example.tmdb.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): NetworkResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful && response.body() != null) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Failure(response.message())
                }
            } catch (e: HttpException) {
                NetworkResult.Failure(e.message)
            } catch (e: Exception) {
                NetworkResult.Failure(e.localizedMessage)
            }
        }
    }
}