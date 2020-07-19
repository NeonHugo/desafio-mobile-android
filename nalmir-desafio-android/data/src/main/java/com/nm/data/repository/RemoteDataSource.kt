package com.nm.data.repository

import com.nm.domain.entity.Cart
import com.nm.infra.net.Results

interface RemoteDataSource {
    suspend fun getCart(): Results<Cart>
}
