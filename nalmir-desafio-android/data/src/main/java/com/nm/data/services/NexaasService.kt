package com.nm.data.services

import com.nm.data.model.ProductSchema
import com.nm.domain.entity.Product
import retrofit2.Response
import retrofit2.http.GET

interface NexaasService {

    @GET("api/data.json")
    suspend fun getProductList(): Response<List<ProductSchema>>

}
