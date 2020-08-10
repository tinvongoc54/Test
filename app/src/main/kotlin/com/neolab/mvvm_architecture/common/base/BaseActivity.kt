package com.neolab.mvvm_architecture.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.neolab.mvvm_architecture.utils.liveData.EventObserver
import kotlin.reflect.KClass
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 *
 * @viewModel -> name view model
 * @classViewModel -> class view model
 * @viewBinding -> class binding
 * @initialize -> init UI, adapter, listener...
 * @onSubscribeObserver -> subscribe observer live data
 *
 */

abstract class BaseActivity<viewModel : BaseViewModel,
        viewBinding : ViewBinding>(classViewModel: KClass<viewModel>) : AppCompatActivity() {

    protected val viewModel: viewModel by viewModel(classViewModel)
    protected lateinit var viewBinding: viewBinding
    abstract fun inflateViewBinding(inflater: LayoutInflater): viewBinding

    protected abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = inflateViewBinding(layoutInflater)
        setContentView(viewBinding.root)
        initialize()
        onSubscribeObserver()
    }

    open fun onSubscribeObserver() {
        viewModel.run {
            isLoading.observe(this@BaseActivity, EventObserver {
                // TODO show/hide loading
            })
            errorMessage.observe(this@BaseActivity, EventObserver {
                // TODO show message
            })
        }
    }
}
