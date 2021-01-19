package com.luthfi.awesomeapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.luthfi.awesomeapp.R
import com.luthfi.awesomeapp.adapter.ImageGridAdapter
import com.luthfi.awesomeapp.data.repository.api.ApiResponse
import com.luthfi.awesomeapp.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val gridAdapter = ImageGridAdapter()

        viewModel.imageList.observe(this, {
            if (it != null) {
                when (it) {
                    is ApiResponse.Success -> {
                        val coverImage = it.data?.get(0)?.src?.landscape
                        Glide.with(this).load(coverImage).into(binding.ivCover)
                        gridAdapter.setImageData(it.data)
                    }
                    is ApiResponse.Error -> Toast.makeText(
                        this,
                        it.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        binding.layoutMain.rvImageGrid.layoutManager = GridLayoutManager(this, 2)
        binding.layoutMain.rvImageGrid.adapter = gridAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_grid -> {
                binding.layoutMain.rvImageGrid.layoutManager = GridLayoutManager(this, 2)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}