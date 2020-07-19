package com.nm.domain.repository

import com.nm.domain.entity.Cart
import com.nm.domain.entity.Product
import com.nm.infra.net.Results

interface CartRepository {
    suspend fun getCart(): Results<Cart>
}