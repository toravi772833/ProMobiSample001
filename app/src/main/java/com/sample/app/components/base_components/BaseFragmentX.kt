package com.sample.app.components.base_components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseFragmentX<T : ViewDataBinding>(@LayoutRes private val layout: Int) : BaseFragment() {

    lateinit var mBinding: T
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            mBinding = DataBindingUtil.inflate(inflater, layout, container, false)
            rootView = mBinding.root
            initComponents()
        } else container?.removeView(rootView)
        return rootView!!
    }
}