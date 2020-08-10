package com.neolab.mvvm_architecture.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neolab.mvvm_architecture.utils.DataResult
import com.neolab.mvvm_architecture.utils.liveData.SingleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 */
abstract class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<SingleEvent<Boolean>>()
    val errorMessage = MutableLiveData<SingleEvent<String>>()

    private var loadingCount = 0

    /**
     * Calls api with view model scope
     */
    protected fun <T> viewModelScope(
        liveData: MutableLiveData<T>,
        isShowLoading: Boolean = true,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null,
        onRequest: suspend CoroutineScope.() -> DataResult<T>
    ) {
        viewModelScope.launch {
            showLoading(isShowLoading)
            when (val asynchronousTasks = onRequest(this)) {
                is DataResult.Success -> {
                    onSuccess?.invoke(asynchronousTasks.data) ?: kotlin.run {
                        liveData.value = asynchronousTasks.data
                    }
                }
                is DataResult.Error -> {
                    onError?.invoke(asynchronousTasks.exception) ?: kotlin.run {
                        asynchronousTasks.exception.message?.let {
                            errorMessage.value = SingleEvent(it)
                        }
                    }
                }
            }
            hideLoading(isShowLoading)
        }
    }

    protected fun viewModelScope(
        isShowLoading: Boolean = true,
        onSuccess: (() -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null,
        onRequest: suspend CoroutineScope.() -> DataResult<Any>
    ) {
        viewModelScope.launch {
            showLoading(isShowLoading)
            when (val asynchronousTasks = onRequest(this)) {
                is DataResult.Success -> {
                    onSuccess?.invoke()
                }
                is DataResult.Error -> {
                    onError?.invoke(asynchronousTasks.exception) ?: kotlin.run {
                        asynchronousTasks.exception.message?.let {
                            errorMessage.value = SingleEvent(it)
                        }
                    }
                }
            }
            hideLoading(isShowLoading)
        }
    }

    protected fun showLoading(isShowLoading: Boolean) {
        if (!isShowLoading) return
        loadingCount++
        if (isLoading.value?.peekContent() != true) isLoading.value = SingleEvent(true)
    }

    protected fun hideLoading(isShowLoading: Boolean) {
        if (!isShowLoading) return
        loadingCount--
        if (loadingCount <= 0) {
            // reset loadingCount
            loadingCount = 0
            isLoading.value = SingleEvent(true)
        }
    }
}
