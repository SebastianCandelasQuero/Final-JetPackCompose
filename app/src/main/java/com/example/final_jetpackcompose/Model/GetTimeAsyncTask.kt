package com.example.final_jetpackcompose.Model

import android.os.AsyncTask
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class GetTimeAsyncTask(
    private val timeZone: String,
    private val onTimeFetchedListener: OnTimeFetchedListener
) : AsyncTask<Void, Void, String>() {

    interface OnTimeFetchedListener {
        fun onTimeFetched(result: String)
    }

    override fun doInBackground(vararg params: Void?): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone(timeZone)

        return try {
            val currentTime = Date()
            sdf.format(currentTime)
        } catch (e: Exception) {
            e.printStackTrace()
            "Error obteniendo la hora"
        }
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        onTimeFetchedListener.onTimeFetched(result)
    }
}