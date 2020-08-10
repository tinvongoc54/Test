package com.neolab.mvvm_architecture.model.request

import com.google.gson.annotations.SerializedName

class SampleRequest(
    @SerializedName("sample_property")
    val sampleProperty: Any
)
