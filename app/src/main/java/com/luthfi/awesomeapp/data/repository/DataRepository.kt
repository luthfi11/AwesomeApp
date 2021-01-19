package com.luthfi.awesomeapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthfi.awesomeapp.data.model.Image
import com.luthfi.awesomeapp.data.model.Response
import com.luthfi.awesomeapp.data.repository.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback

class DataRepository {

    private val apiService = ApiConfig.provideApiService()

    fun getImageList() : LiveData<List<Image>> {
        val result = MutableLiveData<List<Image?>?>()

        apiService.getAllImage().enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                response.body()?.photos.let {
                    result.postValue(it)
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}