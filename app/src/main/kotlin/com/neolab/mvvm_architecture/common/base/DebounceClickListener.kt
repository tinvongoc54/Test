package com.neolab.mvvm_architecture.common.base

import android.os.SystemClock
import android.view.View

/**
 * Copyright © 2020 Neolab VN.
 * @author Nguyen Minh Tam
 *
 * Custom Click Listener to prevent multiple fast clicks
 */

abstract class DebounceClickListener(private val minInterval: Int = 1000) : View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(v: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < minInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        performClick(v)
    }

    abstract fun performClick(v: View?)
}
