package com.garcia.openweatherapp.util.ui

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.garcia.openweatherapp.R

class ProgressBarLayout : ConstraintLayout {

    private var progressBarText: TextView? = null

    constructor(context: Context) : super(context) {
        (getContext() as Activity).layoutInflater.inflate(R.layout.view_loading, this)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    private fun initialize() {
        progressBarText = findViewById(R.id.progressbar_text)
    }

    fun setMessage(message: String) {
        initialize()
        progressBarText!!.text = message
    }
}
