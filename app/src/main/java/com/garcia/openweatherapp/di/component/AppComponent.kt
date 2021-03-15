package com.garcia.openweatherapp.di.component

import com.garcia.openweatherapp.MyApp
import com.garcia.openweatherapp.di.module.ActivityMainBindingModule
import com.garcia.openweatherapp.di.module.ApiModule
import com.garcia.openweatherapp.di.module.AppModule
import com.garcia.openweatherapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityMainBindingModule::class,
        ApiModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApp> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: MyApp): Builder

        fun build(): AppComponent

    }

    override fun inject(app: MyApp)

    fun exposeApp(): MyApp
}