package com.garcia.openweatherapp.di.module.fragmentModule

import androidx.lifecycle.ViewModel
import com.garcia.openweatherapp.di.annotation.FragmentScoped
import com.garcia.openweatherapp.di.annotation.ViewModelKey
import com.garcia.openweatherapp.ui.fragment.HomeFragment
import com.garcia.openweatherapp.ui.fragment.WeatherFragment
import com.garcia.openweatherapp.ui.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainFragmentsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeWeatherFragment(): WeatherFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindDetailViewModel(viewModel: MainViewModel): ViewModel
}