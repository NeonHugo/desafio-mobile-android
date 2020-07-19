package com.nm.domain.usecase

import com.nm.domain.entity.Cart
import com.nm.domain.repository.CartRepository
import com.nm.infra.net.Results

class CartUseCaseImpl(
    private val cartRepository: CartRepository
) : CartUseCase {
    override suspend fun showCart(): Results<Cart> {
        return cartRepository.getCart()
    }
}