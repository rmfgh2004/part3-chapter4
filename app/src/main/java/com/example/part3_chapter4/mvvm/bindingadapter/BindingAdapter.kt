package com.example.part3_chapter4.mvvm.bindingadapter

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.part3_chapter4.mvvm.model.Image
import androidx.core.graphics.toColorInt

@BindingAdapter("image")
fun ImageView.setImage(image: Image?) {
    if (image == null) return

    setBackgroundColor(image.color.toColorInt())
    load(image.url) {
        crossfade(300)
    }
}