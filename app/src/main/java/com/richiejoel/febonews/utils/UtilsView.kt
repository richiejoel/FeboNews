package com.richiejoel.febonews.utils

import android.app.Activity
import android.content.Intent
import com.richiejoel.febonews.ui.view.activities.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class UtilsView {

    companion object {

        fun goToMainActivity(activity: Activity) {
            val intentDashboard = Intent(activity, MainActivity::class.java)
            intentDashboard.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intentDashboard)
            activity.finish()
        }

        fun getFormatDateFromDate(date: Date): String{
            val formatOut = SimpleDateFormat("dd-MM-yyyy")
            return formatOut.format(date).toString()
        }
    }
}