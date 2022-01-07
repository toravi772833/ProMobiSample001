package com.sample.app.common

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.sample.app.R

object Alert {

    private var alertDialog: AlertDialog? = null

    /**
     *This function helps to show common alert dialog
     *
     * @mContext : Current activity context
     * @message : Display message for alert dialog
     * @success : CallBack for on ok click
     * @cancel : CallBack for dialog cancellation
     */
    fun showAlert(mContext: Context, message: String?, success: () -> Unit = {}, cancel: () -> Unit = {}) {
        try {
            if (alertDialog != null && alertDialog!!.isShowing) {
                alertDialog!!.dismiss()
                alertDialog = null
            }

            val builder = AlertDialog.Builder(mContext)
            builder.setMessage(message)
            builder.setCancelable(false)

            builder.setPositiveButton(mContext.getString(R.string.alert_positive_btn_title)) { dialog, _ ->
                dialog.dismiss()
                success()
            }

            builder.setOnKeyListener { dialog, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss()
                    cancel()
                }
                false
            }

            alertDialog = builder.create()
            alertDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * This function helps to show Android default Toast message
     *
     * @mContext : Current activity context
     * @message : Message should show in toast message
     * @isLengthLong: How long toast should appear can be choose from here
     *                  By default @isLengthLong = false i.e Toast.LENGTH_SHORT
     *                  If @isLengthLong = true i.e Toast.LENGTH_LONG
     */
    fun showToast(mContext: Context, message: String, isLengthLong: Boolean = false) {
        Toast.makeText(
            mContext,
            message,
            if (isLengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * This function helps to show Android default SnackBar with given message
     *
     * @rootView : In which layout SnackBar should appear
     * @message : Message should show in SnackBar message
     * @isLengthLong: How long toast should appear can be choose from here
     *                  By default @isLengthLong = false i.e SnackBar.LENGTH_SHORT
     *                  If @isLengthLong = true i.e SnackBar.LENGTH_LONG
     */
    fun showSnackBar(rootView: View, message: String, isLengthLong: Boolean = false) {
        Snackbar.make(
            rootView,
            message,
            if (isLengthLong) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT
        ).show()
    }


    /**
     *This function helps to show confirm alert to user
     *
     * @mContext : Current activity context
     * @message : Display message for confirm dialog
     * @success : CallBack for positive button click
     * @cancel : CallBack for negative button and dialog cancellation
     */
    fun confirmAlert(mContext: Context, message: String, success: () -> Unit, cancel: () -> Unit = {}) = confirmAlert(
        mContext,
        message,
        mContext.getString(R.string.alert_positive_btn_title),
        mContext.getString(R.string.alert_negative_btn_title),
        success,
        cancel
    )

    /**
     *This function helps to show confirm alert to user
     *
     * @mContext : Current activity context
     * @message : Display message for confirm dialog
     * @positiveButton : Title for positive button of Confirm dialog
     * @negativeButton : Title for negative button of Confirm dialog
     * @success : CallBack for positive button click
     * @cancel : CallBack for negative button and dialog cancellation
     */
    fun confirmAlert(
        mContext: Context, message: String, positiveButton: String,
        negativeButton: String, success: () -> Unit, cancel: () -> Unit = {}
    ) = confirmAlert(mContext, null, message, positiveButton, negativeButton, success, cancel)

    fun confirmAlert(
        mContext: Context, title: String? = null, message: String, positiveButton: String,
        negativeButton: String, success: () -> Unit, cancel: () -> Unit = {}
    ) {
        try {
            if (alertDialog != null && alertDialog!!.isShowing) {
                alertDialog?.dismiss()
                alertDialog = null
            }

            val builder = AlertDialog.Builder(mContext)
            builder.setMessage(message)
            builder.setCancelable(false)

            if (title.isNotEmpty()) builder.setTitle(title)

            builder.setPositiveButton(positiveButton) { dialog, _ ->
                dialog.dismiss()
                success()
            }

            builder.setNegativeButton(negativeButton) { dialog, _ ->
                dialog.dismiss()
                cancel()
            }

            builder.setOnKeyListener { dialog, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss()
                    cancel()
                }
                false
            }

            alertDialog = builder.create()
            alertDialog?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}