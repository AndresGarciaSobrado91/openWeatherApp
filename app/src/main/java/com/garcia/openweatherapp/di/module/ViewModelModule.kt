package com.garcia.openweatherapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.garcia.openweatherapp.di.factory.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory):
            ViewModelProvider.Factory
}