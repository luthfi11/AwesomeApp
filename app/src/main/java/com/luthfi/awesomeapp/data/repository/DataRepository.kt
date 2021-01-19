package com.luthfi.awesomeapp.data.repository

import com.luthfi.awesomeapp.data.model.Image
import com.luthfi.awesomeapp.data.repository.api.ApiResponse
import com.luthfi.awesomeapp.data.repository.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataRepository(private val apiService: ApiService) {

    suspend fun getImageList() : Flow<ApiResponse<List<Image?>?>> = flow {
        try {
            val response = apiService.getAllImage()
            emit(ApiResponse.Success(response.photos))
        } catch (ex: Exception) {
            emit(ApiResponse.Error(ex.toString()))
        }
    }.flowOn(Dispatchers.IO)
}
