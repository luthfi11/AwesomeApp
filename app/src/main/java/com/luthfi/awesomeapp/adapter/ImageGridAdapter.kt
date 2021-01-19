package com.luthfi.awesomeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luthfi.awesomeapp.R
import com.luthfi.awesomeapp.data.model.Image
import com.luthfi.awesomeapp.databinding.ItemImageGridBinding

class ImageGridAdapter(): RecyclerView.Adapter<ImageGridAdapter.ViewHolder>() {

    private val imageList = arrayListOf<Image?>()

    fun setImageData(newData: List<Image?>?) {
        if (newData == null) return

        imageList.clear()
        imageList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image_grid, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemImageGridBinding.bind(view)

        fun bind(image: Image?) {
            with(binding) {
                image?.let {
                    Glide.with(root.context).load(it.src?.medium).into(ivImageHome)

                    tvPhotographerName.text = it.photographer
                    tvPhotographerUrl.text = it.photographerUrl
                }
            }
        }
    }
}