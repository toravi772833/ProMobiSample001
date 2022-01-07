package com.sample.app.components.base_components

import androidx.appcompat.app.AppCompatActivity
import com.sample.app.common.CommonUtils

abstract class BaseActivity : AppCompatActivity() {

    var currentRunningFragment: BaseFragment? = null

    var exchangeDataString: String = ""

    override fun onBackPressed() {
        CommonUtils.hideKeyboard(this)
        if (currentRunningFragment == null || !currentRunningFragment!!.onBackPressed()) super.onBackPressed()
    }
}