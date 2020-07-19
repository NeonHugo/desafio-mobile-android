package com.nm.data.di

import com.nm.data.mapper.ProductSchemaListToCartMapper
import com.nm.data.mapper.ProductSchemaToProductMapper
import com.nm.data.model.ProductSchema
import com.nm.domain.entity.Product
import com.nm.infra.net.data.Mapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataModule {
    val mapperModule = module {
        single<Mapper<ProductSchema, Product>>(named("pStoP")) { ProductSchemaToProductMapper() }

        single(named("pslCart")) {
            ProductSchemaListToCartMapper(
                get(
                    named("pStoP")
                )
            )
        }
    }
}