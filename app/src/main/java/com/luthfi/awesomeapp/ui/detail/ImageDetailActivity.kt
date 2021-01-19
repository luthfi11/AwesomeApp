package com.luthfi.awesomeapp.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.luthfi.awesomeapp.R
import com.luthfi.awesomeapp.data.model.Image
import com.luthfi.awesomeapp.databinding.ActivityImageDetailBinding

class ImageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = intent.getParcelableExtra<Image>("data")

        data?.let {
            with(binding) {
                Glide.with(this@ImageDetailActivity).load(it.src?.portrait).placeholder(R.color.gray).into(ivImageDetail)
                tvPhotographerName.text = it.photographer
                tvPhotographerUrl.text = it.photographerUrl
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}