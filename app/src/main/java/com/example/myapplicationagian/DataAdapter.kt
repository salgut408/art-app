//package com.example.myapplicationagian
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.myapplicationagian.databinding.ItemArtWorkBinding
//
//class DataAdapter(val context: Context, val artWorks: List<ArtworkObject>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
////    private lateinit var binding: ItemArtWorkBinding
//    val binding = ItemArtWorkBinding.inflate(LayoutInflater.from(context))
//
//    // the 3 methds needed from extending recyvler view adapter
//    //inflates and wraps layout
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
////return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_art_work, parent, false))
////        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_art_work, parent, false))
//        return ViewHolder(ItemArtWorkBinding.inflate(LayoutInflater.from(parent.context)))
//    }
//    //tells size
//    override fun getItemCount() = artWorks.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        // grab artwork at this position
//        val artwork = artWorks[position]
//        holder.bind(artwork)
//    }
//
//    // this the viewholder
//    inner class ViewHolder(private var binding: ItemArtWorkBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(artwork: ArtworkObject) {
//
//        binding.tvName.text = artwork.title
//            binding.tvArtistName.text = artwork.artistTitle
//            binding.executePendingBindings()
//            Glide.with(context).load(artwork.getArtImageUrl()).into(binding.imageView)
//
//
//        }
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
