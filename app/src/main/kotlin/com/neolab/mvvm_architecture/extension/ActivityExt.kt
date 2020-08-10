package com.neolab.mvvm_architecture.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/6/20.
 */

fun FragmentActivity?.hideKeyboard() {
    this?.let {
        val inputManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputManager?.let {
            val v = this.currentFocus
            v?.let { view ->
                inputManager.hideSoftInputFromWindow(
                    view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }
}
