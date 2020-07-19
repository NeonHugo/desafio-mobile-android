package com.nm.data.repository

import com.nm.domain.entity.Cart
import com.nm.domain.repository.CartRepository
import com.nm.infra.net.Results

class CartRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : CartRepository {

    override suspend fun getCart(): Results<Cart> {
        return remoteDataSource.getCart()
    }
}