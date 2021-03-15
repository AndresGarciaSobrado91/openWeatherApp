package com.garcia.openweatherapp.ui.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class ScopedViewModel : ObservableViewModel() {

    // SupervisorJob allows children jobs to fail independently
    private val job = SupervisorJob()

    protected val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}