package com.githubexamples.avik.matchMaking.utils

import android.view.View


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

