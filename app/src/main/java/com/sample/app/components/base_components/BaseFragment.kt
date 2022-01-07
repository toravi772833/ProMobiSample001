package com.sample.app.components.base_components

import android.content.Context
import androidx.fragment.app.Fragment
import com.sample.app.common.LOGApp
import com.sample.app.components.support.FragmentComponents

abstract class BaseFragment : Fragment(), FragmentComponents {

    lateinit var mActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as BaseActivity
    }

    override fun onResume() {
        super.onResume()
        LOGApp.i("CurrentRunningScreen:", this.javaClass.simpleName)
        mActivity.currentRunningFragment = this
    }

    override fun onBackPressed(): Boolean = false

    override fun internalProgress(showProgress: Boolean) {
        // It's an Optional override function to it's child class
    }
}