package com.neolab.mvvm_architecture.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/6/20.
 */

fun FragmentActivity?.hideKeyboard() {
    if (this != null) {
        val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        if (inputManager != null) {
            val v = this.currentFocus
            if (v != null) {
                inputManager.hideSoftInputFromWindow(
                    v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }
}