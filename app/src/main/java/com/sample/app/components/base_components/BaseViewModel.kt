package com.sample.app.components.base_components

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sample.app.application.SampleApplication

abstract class BaseViewModel(private val application: SampleApplication) : AndroidViewModel(application) {

    private val app: Application = application

    val showAlert = MutableLiveData<String>()
    val showSnackBar = MutableLiveData<String>()

    fun getString(@StringRes resource: Int): String = app.getString(resource)
}