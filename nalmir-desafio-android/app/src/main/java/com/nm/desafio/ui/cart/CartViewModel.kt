package com.nm.desafio.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nm.domain.entity.Cart
import com.nm.domain.usecase.CartUseCase
import com.nm.infra.base.BaseViewModel
import com.nm.infra.net.ErrorResults
import com.nm.infra.net.SuccessResults

class CartViewModel(
    private val cartUseCase: CartUseCase
) : BaseViewModel() {

    private val _cart = MutableLiveData<Cart>()

    val cart: LiveData<Cart> get() = _cart

    fun getCart() {

        launchDataLoad {
            when (val resposta = cartUseCase.showCart()) {
                is SuccessResults -> {
                    _cart.value = resposta.body
                }
                is ErrorResults -> {
                    _error.value = true
                }
                else -> {
                    _error.value = true
                }
            }
        }
    }

    override fun doOnError(error: Exception) {
        _error.value = true
    }

}