package com.example.myapplicationagian

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplicationagian.api.artWorks

@BindingAdapter("pictureUrl")
fun bindUriToImage(imageView: ImageView, strUrl: String?) {
    Glide.with(imageView.context)
        .load(strUrl)
        .into(imageView)
}





//@BindingAdapter("tvName")
//fun bindtvNameToArtistName(textView: TextView, name: String){
//    val context = textView.context
//    textView.text = context.getString(R.string.art)
//}