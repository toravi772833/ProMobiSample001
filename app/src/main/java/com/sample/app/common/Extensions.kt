package com.sample.app.common

import android.text.TextUtils

fun String?.isEmpty() = TextUtils.isEmpty((this ?: "").trim())

fun String?.isNotEmpty() = !this.isEmpty()

fun String?.getTrim() = if (this.isEmpty()) "" else this!!.trim()
