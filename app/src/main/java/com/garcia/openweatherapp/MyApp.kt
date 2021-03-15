package com.garcia.openweatherapp

import com.garcia.openweatherapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    companion object {
        const val API_KEY = "760a5ec34ccada365e98e6a5783b563c"
    }
}