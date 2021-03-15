package com.garcia.openweatherapp.util.ui

import android.os.Handler
import android.view.View
import java.util.concurrent.atomic.AtomicInteger

class LoadingViewManager {

    var loadingView: View? = null
    private val loadingCount = AtomicInteger(0)
    private val timeoutHandler = Handler()

    fun showLoading(message: String, autoClear: Boolean = true) {
        loadingView?.let{
            (it as ProgressBarLayout).setMessage(message)
        }
        updateCount(1, autoClear)
    }

    fun hideLoading() {
        updateCount(-1)
    }

    fun clear() {
        updateCount(0 - loadingCount.get())
    }

    fun isLoading() = loadingView?.visibility == View.VISIBLE

    @Synchronized
    private fun updateCount(value: Int, autoClear: Boolean = true) {
        loadingView?.let {
            timeoutHandler.removeCallbacksAndMessages(null)
            val newCount = loadingCount.addAndGet(value)
            if (newCount < 0)
                loadingCount.set(0)
            it.visibility = if (newCount > 0) View.VISIBLE else View.GONE
            if (newCount > 0 && autoClear)
                timeoutHandler.postDelayed({ clear() }, 10000)
        }
    }
}