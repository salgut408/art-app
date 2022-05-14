package com.example.myapplicationagian.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationagian.ArtworkObject
import com.example.myapplicationagian.databinding.ItemArtWorkBinding

class MainAdapter(private val clickListener: ArtworkListener) :
    ListAdapter<ArtworkObject, MainAdapter.ArtworkViewHolder>(DiffCallback) {



    companion object DiffCallback : DiffUtil.ItemCallback<ArtworkObject>() {
        override fun areItemsTheSame(oldItem: ArtworkObject, newItem: ArtworkObject): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ArtworkObject, newItem: ArtworkObject): Boolean {
            return oldItem.score == newItem.score
        }

    }

    class ArtworkViewHolder(var binding: ItemArtWorkBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(artwork: ArtworkObject) {
            binding.artwork = artwork
            binding.executePendingBindings()
        }
    }



    class ArtworkListener(val clickListener: (artwork: ArtworkObject) -> Unit) {
        fun onClick(artwork: ArtworkObject) = clickListener(artwork)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        val withDataBinding: ItemArtWorkBinding = ItemArtWorkBinding.inflate(LayoutInflater.from(parent.context))
        return ArtworkViewHolder(withDataBinding)
    }


    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        val artwork = getItem(position)

        holder.also {
            it.itemView.setOnClickListener{
                clickListener.onClick(artwork)
            }
            it.bind(artwork)
        }
    }
}