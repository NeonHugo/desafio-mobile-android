package com.nm.infra.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel() : DefaultViewModel() {

    protected val _loading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean> get() = _loading

    protected val _error = MutableLiveData<Boolean>()

    val error: LiveData<Boolean> get() = _error

    protected fun launchDataLoad(
        block: suspend () -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                _loading.value = true
                block()
            } catch (error: Exception) {
                doOnError(error)
            } finally {
                _loading.value = false
            }
        }
    }

    open fun doOnError(error: Exception) {}
}