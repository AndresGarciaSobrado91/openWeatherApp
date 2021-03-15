package com.garcia.openweatherapp.ui.base

import android.os.Bundle
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.internal.Beta
import javax.inject.Inject

@Beta
abstract class DaggerAppCompatActivity : AppCompatActivity, HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    constructor() : super() {}

    @ContentView
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return androidInjector
    }
}