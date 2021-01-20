package com.luthfi.awesomeapp

import androidx.paging.PagingData
import androidx.paging.map
import com.luthfi.awesomeapp.core.model.Image
import com.luthfi.awesomeapp.core.model.ImageSource
import com.luthfi.awesomeapp.ui.main.MainViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @Mock
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var pagingData: Flow<PagingData<Image>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getImageTest() {
        runBlocking {
            `when`(viewModel.imageList).thenReturn(pagingData)
            verify(viewModel.imageList)

            val photographer = mutableListOf<String?>()
            val src = mutableListOf<ImageSource?>()
            val job = launch {
                viewModel.imageList.collectLatest {
                    it.map { data ->
                        {
                            photographer.add(data.photographer)
                            src.add(data.src)
                        }
                    }
                }
            }

            Assert.assertNotNull(photographer)
            Assert.assertNotNull(src)

            job.cancel()
        }
    }
}