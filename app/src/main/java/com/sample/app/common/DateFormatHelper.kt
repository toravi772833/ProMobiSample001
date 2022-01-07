package com.sample.app.common

import java.text.SimpleDateFormat
import java.util.*

object DateFormatHelper {

    private val serverDateFormat = SimpleDateFormat(AppConstants.AppTimeFormat.SERVER_DATE_FORMAT, Locale.ENGLISH)
    private val displayDateFormat = SimpleDateFormat(AppConstants.AppTimeFormat.DISPLAY_DATE_FORMAT, Locale.ENGLISH)
    private val serverDateTimeFormat = SimpleDateFormat(AppConstants.AppTimeFormat.SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
    private val displayDateTimeFormat = SimpleDateFormat(AppConstants.AppTimeFormat.DISPLAY_DATE_TIME_FORMAT, Locale.ENGLISH)

    fun getDisplayDateFromServerDate(serverDate: String?): String {
        if (serverDate.isEmpty()) return "---"
        return try {
            serverDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            displayDateFormat.timeZone = TimeZone.getDefault()
            displayDateFormat.format(serverDateFormat.parse(serverDate.getTrim())!!)
        } catch (e: Exception) {
            LOGApp.println(e.message)
            "---"
        }
    }

    fun getDisplayDateTimeFromServerDate(serverDate: String?): String {
        if (serverDate.isEmpty()) return "---"
        return try {
            serverDateTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
            displayDateTimeFormat.timeZone = TimeZone.getDefault()
            displayDateTimeFormat.format(serverDateTimeFormat.parse(serverDate.getTrim())!!)
        } catch (e: Exception) {
            LOGApp.println(e.message)
            "---"
        }
    }
}