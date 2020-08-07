package com.neolab.mvvm_architecture.di

import com.neolab.mvvm_architecture.data.repository.task.TaskRepository
import com.neolab.mvvm_architecture.data.repository.task.DefaultTaskRepository
import org.koin.dsl.module

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 */

val repositoryModule = module {
    single { providerTaskRepository() }
}

fun providerTaskRepository(): TaskRepository{
    return DefaultTaskRepository()
}
