package com.neolab.mvvm_architecture.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
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

abstract class BaseDialogFragment<viewModel : BaseViewModel,
        viewBinding : ViewBinding>(classViewModel: KClass<viewModel>) : DialogFragment() {

    protected val viewModel: viewModel by viewModel(classViewModel)

    private var _viewBinding: viewBinding? = null
    protected val viewBinding get() = _viewBinding!! // ktlint-disable
    abstract fun inflateViewBinding(inflater: LayoutInflater): viewBinding

    protected abstract fun initialize()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = inflateViewBinding(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        onSubscribeObserver()
    }

    /**
     * Fragments outlive their views. Make sure you clean up any references to
     * the binding class instance in the fragment's onDestroyView() method.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    open fun onSubscribeObserver() {
        viewModel.run {
            isLoading.observe(viewLifecycleOwner, EventObserver {
                // TODO show/hide loading
            })
            errorMessage.observe(viewLifecycleOwner, EventObserver {
                // TODO show message
            })
        }
    }
}