package com.luthfi.awesomeapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image (
    @field:SerializedName("src")
    val src: ImageSource? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("avg_color")
    val avgColor: String? = null,

    @field:SerializedName("photographer")
    val photographer: String? = null,

    @field:SerializedName("photographer_url")
    val photographerUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("photographer_id")
    val photographerId: Int? = null,

    @field:SerializedName("liked")
    val liked: Boolean? = null,

    @field:SerializedName("height")
    val height: Int? = null
): Parcelable