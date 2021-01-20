package com.luthfi.awesomeapp.core.model

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("next_page")
	val nextPage: String? = null,

    @field:SerializedName("per_page")
	val perPage: Int? = null,

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("photos")
	val photos: List<Image>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)
