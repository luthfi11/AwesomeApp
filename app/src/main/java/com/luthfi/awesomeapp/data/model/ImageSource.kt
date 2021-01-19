package com.luthfi.awesomeapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageSource(
    @field:SerializedName("small")
    val small: String? = null,

    @field:SerializedName("original")
    val original: String? = null,

    @field:SerializedName("large")
    val large: String? = null,

    @field:SerializedName("tiny")
    val tiny: String? = null,

    @field:SerializedName("medium")
    val medium: String? = null,

    @field:SerializedName("large2x")
    val large2x: String? = null,

    @field:SerializedName("portrait")
    val portrait: String? = null,

    @field:SerializedName("landscape")
    val landscape: String? = null
): Parcelable