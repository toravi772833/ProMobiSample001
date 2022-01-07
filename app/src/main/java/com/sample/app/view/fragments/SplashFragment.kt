package com.sample.app.view.fragments

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.sample.app.components.base_components.BaseFragmentX
import com.sample.app.R
import com.sample.app.databinding.SplashScreenBinding

class SplashFragment : BaseFragmentX<SplashScreenBinding>(R.layout.splash_screen) {

    override fun initComponents() {
        Handler(Looper.getMainLooper()).postDelayed({ findNavController().navigate(R.id.movieReviewsFragment) }, 3000)
    }
}