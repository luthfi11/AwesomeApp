package com.luthfi.awesomeapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.luthfi.awesomeapp.R
import com.luthfi.awesomeapp.adapter.ImageGridAdapter
import com.luthfi.awesomeapp.adapter.ImageListAdapter
import com.luthfi.awesomeapp.data.model.Image
import com.luthfi.awesomeapp.data.repository.api.ApiResponse
import com.luthfi.awesomeapp.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var gridAdapter: ImageGridAdapter
    private lateinit var listAdapter: ImageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        gridAdapter = ImageGridAdapter()
        listAdapter = ImageListAdapter()

        viewModel.imageList.observe(this, imageObserver)
        setGridLayout()
    }

    private val imageObserver = Observer<ApiResponse<List<Image?>?>> {
        if (it != null) {
            when (it) {
                is ApiResponse.Success -> {
                    val coverImage = it.data?.get(0)?.src?.landscape
                    Glide.with(this).load(coverImage).into(binding.ivCover)

                    gridAdapter.setImageData(it.data)
                    listAdapter.setImageData(it.data)
                }
                is ApiResponse.Error -> Toast.makeText(
                    this,
                    it.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setGridLayout() {
        binding.layoutMain.rvImageGrid.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = gridAdapter
        }
    }

    private fun setListLayout() {
        binding.layoutMain.rvImageGrid.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_grid -> {
                setGridLayout()
                true
            }
            R.id.action_list -> {
                setListLayout()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}