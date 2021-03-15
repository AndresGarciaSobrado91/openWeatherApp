package com.garcia.openweatherapp.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.util.ArrayList

abstract class ObservableViewModel: ViewModel() {

    private val observed = ArrayList<LiveData<*>>()

    fun <T> registerObserver(owner: LifecycleOwner, state: LiveData<T>, observer: Observer<T>) {
        state.observe(owner, observer)
        observed.add(state)
    }

    fun removeObservers(owner: LifecycleOwner) {
        observed.forEach { it.removeObservers(owner) }
        observed.clear()
    }
}