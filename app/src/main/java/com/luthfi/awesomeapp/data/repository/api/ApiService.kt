package com.luthfi.awesomeapp.data.repository.api

import com.luthfi.awesomeapp.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: 563492ad6f91700001000001911b901ddc7d4f8ea7543626cdc9a575")
    @GET("curated")
    suspend fun getAllImage(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int? = 10
    ): Response
}