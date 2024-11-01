package com.example.randomchoicegenerator

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.randomchoicegenerator.model.ModelPreferencesManager
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var activityInstance: MainActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityInstance = this

        // initialize sharedPreferences
        ModelPreferencesManager.with(this.application)

        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(this@MainActivity) {}
        }
    }
}
