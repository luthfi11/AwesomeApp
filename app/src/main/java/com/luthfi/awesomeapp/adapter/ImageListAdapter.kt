package com.luthfi.awesomeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luthfi.awesomeapp.R
import com.luthfi.awesomeapp.core.model.Image
import com.luthfi.awesomeapp.databinding.ItemImageGridBinding
import com.luthfi.awesomeapp.util.OnImageClick

class ImageListAdapter(private val onImageClick: OnImageClick) :
    PagingDataAdapter<Image, ImageListAdapter.ImageViewHolder>(ImageComparator) {

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image_list, parent, false)
        )
    }

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemImageGridBinding.bind(view)

        fun bind(image: Image?) {
            with(binding) {
                image?.let {
                    Glide.with(root.context).load(it.src?.medium).into(ivImageHome)

                    tvPhotographerName.text = it.photographer
                    tvPhotographerUrl.text = it.photographerUrl?.drop(23)
                }

                root.setOnClickListener {
                    image?.let { onImageClick.goToDetail(it) }
                }
            }
        }
    }

    object ImageComparator : DiffUtil.ItemCallback<Image>() {

        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }
}