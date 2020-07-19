package com.nm.data.repository

import com.nm.data.model.ProductSchema
import com.nm.data.services.NexaasService
import com.nm.domain.entity.Cart
import com.nm.infra.net.Results
import com.nm.infra.net.data.Mapper
import com.nm.infra.net.data.create

class RemoteDataSourceImpl(
    private val nexaasService: NexaasService,
    private val mapper: Mapper<List<ProductSchema>, Cart>
) : RemoteDataSource {


    override suspend fun getCart(): Results<Cart> {
        return nexaasService.getProductList().create(mapper)
    }
}