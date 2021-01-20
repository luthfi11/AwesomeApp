package com.luthfi.awesomeapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.luthfi.awesomeapp.R
import com.luthfi.awesomeapp.adapter.ImageGridAdapter
import com.luthfi.awesomeapp.adapter.ImageListAdapter
import com.luthfi.awesomeapp.data.model.Image
import com.luthfi.awesomeapp.databinding.ActivityMainBinding
import com.luthfi.awesomeapp.ui.detail.ImageDetailActivity
import com.luthfi.awesomeapp.util.OnImageClick
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : AppCompatActivity(), OnImageClick {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var gridAdapter: ImageGridAdapter
    private lateinit var listAdapter: ImageListAdapter
    private lateinit var imageData: PagingData<Image>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        gridAdapter = ImageGridAdapter(this)
        listAdapter = ImageListAdapter(this)

        lifecycleScope.launch {
            viewModel.imageList.collectLatest {
                imageData = it
            }
        }

        setGridLayout()
    }

    private fun setGridLayout() {
        lifecycleScope.launch {
            gridAdapter.submitData(imageData)
        }

        binding.layoutMain.rvImageGrid.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = gridAdapter
        }

        gridAdapter.addLoadStateListener { loadStateListener(it) }
    }

    private fun setListLayout() {
        lifecycleScope.launch {
            listAdapter.submitData(imageData)
        }

        binding.layoutMain.rvImageGrid.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }

        listAdapter.addLoadStateListener { loadStateListener(it) }
    }

    private fun loadStateListener(loadState: CombinedLoadStates) {
        if (loadState.refresh is LoadState.Loading) {
            binding.shimmerHome.visibility = View.VISIBLE
        } else {
            binding.shimmerHome.visibility = View.GONE
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

    override fun goToDetail(image: Image) {
        val intent = Intent(this, ImageDetailActivity::class.java)
            .apply {
                putExtra("data", image)
            }
        startActivity(intent)
    }
}