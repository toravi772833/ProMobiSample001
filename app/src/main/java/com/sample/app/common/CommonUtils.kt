package com.sample.app.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.startActivity
import com.sample.app.R


object CommonUtils {

    private var dialog: AlertDialog? = null

    /**
     * Request to show progressbar on screen
     *
     *  <p> Progressbar block the user to access UI components,
     */
    @SuppressLint("InflateParams")
    fun showProgress(context: Context) {
        if (dialog == null) {
            val builder = AlertDialog.Builder(context)
            builder.setView(R.layout.progress_view)
            builder.setCancelable(false)
            dialog = builder.create()
            dialog?.let {
                it.requestWindowFeature(Window.FEATURE_NO_TITLE)
                it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
        dialog?.let { if (!it.isShowing) it.show() }
    }

    /**
     * Request to hide blocking progressbar
     *
     * <p> Release application UI for user accessibility
     */
    fun hideProgress() {
        dialog?.let { if (it.isShowing) it.dismiss() }
        dialog = null
    }

    fun hideKeyboardFromView(mActivity: Activity, v: View) {
        val imm = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    /**
     * Request to hide the soft input window from the context
     * of the window that is currently accepting input.
     */
    fun hideKeyboard(activity: Activity?) {
        activity?.currentFocus?.let {
            try {
                val inputManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else false
        } else connManager.activeNetworkInfo?.isConnected ?: false
    }

    fun openLinkInBrowser(mContext: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        mContext.startActivity(browserIntent)
    }
}