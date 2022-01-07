package com.sample.app.common

import android.text.TextUtils
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.app.components.base_components.ViewModelFactory
import java.util.*

fun View.enable() {
    this.isEnabled = true
    this.alpha = 1f
}

fun View.disable() {
    this.isEnabled = false
    this.alpha = .5f
}

fun String?.isEmpty() = TextUtils.isEmpty((this ?: "").trim())

fun String?.isNotEmpty() = !this.isEmpty()

fun String?.getTrim() = if (this.isEmpty()) "" else this!!.trim()

fun <T : ViewModel> getProviderFactory(viewModel: T) = ViewModelFactory(viewModel)

inline fun <reified T : ViewModel> getViewModel(owner: ViewModelStoreOwner, viewModel: T): T {
    return ViewModelProvider(owner, getProviderFactory(viewModel)).get(T::class.java)
}

fun IntRange.random() = Random().nextInt((endInclusive + 1) - start) + start
