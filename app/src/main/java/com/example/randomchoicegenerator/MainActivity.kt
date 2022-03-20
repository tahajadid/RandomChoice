package com.example.randomchoicegenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.randomchoicegenerator.model.ModelPreferencesManager
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)
        // initialize sharedPreferences
        ModelPreferencesManager.with(this.application)

    }
}
