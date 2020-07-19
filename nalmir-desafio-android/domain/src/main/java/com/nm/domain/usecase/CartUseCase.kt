package com.nm.domain.usecase

import com.nm.domain.entity.Cart
import com.nm.infra.net.Results

interface CartUseCase {
    suspend fun showCart() : Results<Cart>
}