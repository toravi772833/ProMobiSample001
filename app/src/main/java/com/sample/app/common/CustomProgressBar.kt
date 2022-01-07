package com.sample.app.common

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import com.sample.app.R

/**
 *  Customized progressbar
 */
class CustomProgressBar : ProgressBar {

    constructor(context: Context) : super(context) {
        this.isIndeterminate = true
        MyDrawableCompat.setColorFilter(
            this.indeterminateDrawable,
            ActivityCompat.getColor(context, R.color.colorPrimary)
        )
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        MyDrawableCompat.setColorFilter(this.indeterminateDrawable, ActivityCompat.getColor(context, R.color.colorPrimary))
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}