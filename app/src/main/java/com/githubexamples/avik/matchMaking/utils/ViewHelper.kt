package com.githubexamples.avik.matchMaking.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.githubexamples.avik.matchMaking.R


fun View.showAsPer(value: Boolean) {
    if (value)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE

}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadProfilePicture(url: String, context: Context) {
    Glide
        .with(context)
        .load(url)
        .circleCrop()
        .placeholder(R.drawable.ic_sample_place_holder)
        .error(R.drawable.ic_sample_place_holder)
        .into(this);
}


