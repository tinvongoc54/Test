package com.neolab.mvvm_architecture.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * This is sample model, u can delete this model.
 */

@Parcelize
data class Task(val name: String) : Parcelable