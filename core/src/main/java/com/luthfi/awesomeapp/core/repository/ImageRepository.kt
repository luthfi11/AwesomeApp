package com.luthfi.awesomeapp.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.luthfi.awesomeapp.core.model.Image
import com.luthfi.awesomeapp.core.api.ApiService
import kotlinx.coroutines.flow.Flow

class ImageRepository(private val apiService: ApiService) {

    fun getImageList() : Flow<PagingData<Image>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingSource(apiService) }
        ).flow
    }
}
