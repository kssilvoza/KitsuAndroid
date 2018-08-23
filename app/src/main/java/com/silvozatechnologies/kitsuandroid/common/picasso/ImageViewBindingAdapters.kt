package com.silvozatechnologies.kitsuandroid.common.picasso

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter(value = "imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Picasso.get()
                .load(imageUrl)
                .into(imageView)
    }
}