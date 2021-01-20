package com.luthfi.awesomeapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.luthfi.awesomeapp.data.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(imageRepository: ImageRepository): ViewModel() {

    val imageList = imageRepository.getImageList().cachedIn(viewModelScope)
}
