package com.sanver.example.core

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sanver.example.R
import com.squareup.picasso.Picasso

object BindingAdapter {
    @JvmStatic
    @androidx.databinding.BindingAdapter("app:visibility")
    fun setVisibility(view: View, isVisible: Boolean) {
        view.visibility = View.GONE
        if (isVisible) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("app:setDrawableLink")
    fun setDrawableLink(view: ImageView, link: String?) {
        if (link.isNullOrEmpty())
            return
        Picasso.get().cancelRequest(view)
        Picasso.get().load(link).into(view)
    }
}