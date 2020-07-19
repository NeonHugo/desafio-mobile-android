package com.nm.infra.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T : Any> LifecycleOwner.bindVM(data: LiveData<T>, function: (id: T) -> Unit) {
    data.observeSmart(this) { function.invoke(it) }
}