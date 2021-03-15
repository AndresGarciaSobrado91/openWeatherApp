package com.garcia.openweatherapp.ui.activitiy

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.garcia.openweatherapp.R
import com.garcia.openweatherapp.databinding.ActivityMainBinding
import com.garcia.openweatherapp.ui.base.DaggerAppCompatActivity
import com.garcia.openweatherapp.util.ui.LoadingViewManager
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var loadingViewManager: LoadingViewManager
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        loadingViewManager.loadingView = findViewById(R.id.loadingView)
    }


}