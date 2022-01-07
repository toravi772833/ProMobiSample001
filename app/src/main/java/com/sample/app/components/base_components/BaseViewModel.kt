package com.sample.app.components.base_components

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel(private val app: Context) : AndroidViewModel(app.applicationContext as Application) {

    val showAlert = MutableLiveData<String>()
    val showSnackBar = MutableLiveData<String>()

    fun getString(@StringRes resource: Int): String = app.getString(resource)
}
