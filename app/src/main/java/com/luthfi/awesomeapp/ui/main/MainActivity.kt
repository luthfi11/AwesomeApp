package com.luthfi.awesomeapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.luthfi.awesomeapp.R
import com.luthfi.awesomeapp.adapter.ImageGridAdapter
import com.luthfi.awesomeapp.adapter.ImageListAdapter
import com.luthfi.awesomeapp.core.model.Image
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
    private lateinit var menu: Menu

    companion object {
        const val COVER_IMAGE = "https://images.pexels.com/photos/1376201/pexels-photo-1376201.jpeg?" +
                "auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        Glide.with(this).load(COVER_IMAGE).into(binding.ivCover)

        gridAdapter = ImageGridAdapter(this)
        listAdapter = ImageListAdapter(this)

        initData()
        setGridLayout()
    }

    private fun initData() {
        lifecycleScope.launch {
            viewModel.imageList.collectLatest {
                imageData = it
            }
        }
    }

    private fun setGridLayout() {
        lifecycleScope.launch { gridAdapter.submitData(imageData) }

        binding.layoutMain.rvImage.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = gridAdapter
        }

        gridAdapter.addLoadStateListener { loadStateListener(it) }
    }

    private fun setListLayout() {
        lifecycleScope.launch { listAdapter.submitData(imageData) }

        binding.layoutMain.rvImage.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }

        listAdapter.addLoadStateListener { loadStateListener(it) }
    }

    private fun loadStateListener(loadState: CombinedLoadStates) {
        binding.shimmerHome.visibility = if (loadState.refresh is LoadState.Loading) View.VISIBLE else View.GONE
        binding.layoutError.root.visibility = if (loadState.refresh is LoadState.Error) View.VISIBLE else View.GONE
        binding.layoutError.btnRetry.setOnClickListener {
            gridAdapter.retry()
        }
    }

    private fun setGridIconActive() {
        menu.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_active)
        menu.getItem(1).icon = ContextCompat.getDrawable(this, R.drawable.ic_list)
    }

    private fun setListIconActive() {
        menu.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_grid)
        menu.getItem(1).icon = ContextCompat.getDrawable(this, R.drawable.ic_list_active)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        this.menu = menu
        setGridIconActive()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_grid -> {
                setGridLayout()
                setGridIconActive()
                true
            }
            R.id.action_list -> {
                setListLayout()
                setListIconActive()
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