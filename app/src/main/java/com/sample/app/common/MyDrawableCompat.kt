package com.sample.app.common

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build

object MyDrawableCompat {

    fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilter(color, BlendMode.MULTIPLY)
        else drawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
    }
}