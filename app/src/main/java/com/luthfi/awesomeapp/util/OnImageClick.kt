package com.luthfi.awesomeapp.util

import com.luthfi.awesomeapp.data.model.Image

interface OnImageClick {
    fun goToDetail(image: Image)
}