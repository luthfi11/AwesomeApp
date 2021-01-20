package com.luthfi.awesomeapp.data.repository.api

import com.luthfi.awesomeapp.BuildConfig
import com.luthfi.awesomeapp.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    @Headers("Authorization: $API_KEY")
    @GET("curated")
    suspend fun getAllImage(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int? = 10
    ): Response
}