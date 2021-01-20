package com.luthfi.awesomeapp.util

import com.luthfi.awesomeapp.core.model.Image

interface OnImageClick {
    fun goToDetail(image: Image)
}