package com.sample.app.components.support

interface FragmentComponents {

    fun initComponents()

    fun onBackPressed(): Boolean

    fun internalProgress(showProgress: Boolean)
}