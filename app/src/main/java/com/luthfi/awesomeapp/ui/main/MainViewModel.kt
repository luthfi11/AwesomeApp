package com.luthfi.awesomeapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.luthfi.awesomeapp.data.repository.DataRepository

class MainViewModel(dataRepository: DataRepository): ViewModel() {

    val imageList = liveData {
        emitSource(dataRepository.getImageList().asLiveData())
    }
}
