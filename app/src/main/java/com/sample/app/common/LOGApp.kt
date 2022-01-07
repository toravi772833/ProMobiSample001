package com.sample.app.common

import android.util.Log
import com.sample.app.BuildConfig

/**
 * Application logger class
 */
object LOGApp {

    private var ENABLE_LOG = BuildConfig.DEBUG

    fun e(tag: String, message: String) {
        if (ENABLE_LOG)
            Log.e(tag, message)
    }

    fun i(tag: String, message: String) {
        if (ENABLE_LOG)
            Log.i(tag, message)
    }

    fun w(tag: String, message: String) {
        if (ENABLE_LOG)
            Log.w(tag, message)
    }

    fun v(tag: String, message: String) {
        if (ENABLE_LOG)
            Log.v(tag, message)
    }

    fun d(tag: String, message: String) {
        if (ENABLE_LOG)
            Log.d(tag, message)
    }

    fun println(message: String?) {
        if (ENABLE_LOG) print(message)
    }
}