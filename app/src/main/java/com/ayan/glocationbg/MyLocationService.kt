package com.ayan.glocationbg

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.location.LocationResult
import java.lang.StringBuilder


class MyLocationService : BroadcastReceiver() {

    companion object {
        val ACTION_PROGRESS_UPDATE = "com.ayan.glocationbg.update_location"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent != null){
            val action = intent!!.action
            if(action.equals(ACTION_PROGRESS_UPDATE)){
                val result = LocationResult.extractResult(intent!!)
                if(result != null){
                    val location = result.lastLocation
                    val location_string = StringBuilder(location.latitude.toString())
                        .append(" / ").append(location.longitude.toString())
                    try{
                        MainActivity.getMainInstance().updateTextView(location_string.toString())
                    }catch (e: Exception){
                        //If the app is in killed state
                        Toast.makeText(context,location_string, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}
