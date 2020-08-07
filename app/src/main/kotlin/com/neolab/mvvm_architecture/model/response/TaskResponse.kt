package com.neolab.mvvm_architecture.model.response

import com.google.gson.annotations.SerializedName

data class TaskResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("task_name")
    val name : String
)