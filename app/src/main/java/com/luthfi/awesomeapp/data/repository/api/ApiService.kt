package com.luthfi.awesomeapp.data.repository.api

import com.luthfi.awesomeapp.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Authorization: 563492ad6f91700001000001911b901ddc7d4f8ea7543626cdc9a575")
    @GET("curated?per_page=16")
    suspend fun getAllImage() : Response
}