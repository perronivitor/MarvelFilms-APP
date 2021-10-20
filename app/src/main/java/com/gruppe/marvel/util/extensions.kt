package com.gruppe.marvel.util

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, {
        it?.let(observer)
    })
}

fun ImageView.load(url: String?){
    url?.let {
        Glide.with(this).load(it).centerCrop().into(this)
    }
}

fun <T> List<T>.toArrayList(): ArrayList<T>{
    return ArrayList(this)
}