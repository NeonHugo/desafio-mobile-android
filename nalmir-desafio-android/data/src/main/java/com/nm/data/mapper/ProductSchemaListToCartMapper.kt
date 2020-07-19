package com.nm.data.mapper

import com.nm.data.model.ProductSchema
import com.nm.domain.entity.Cart
import com.nm.infra.net.data.Mapper

class ProductSchemaListToCartMapper(
    private val mapper: ProductSchemaToProductMapper
) : Mapper<List<ProductSchema>, Cart>() {

    override fun transform(items: List<ProductSchema>): Cart {
        val cart = Cart()

        items.forEach { productSchema ->
            cart.items.add(mapper.transform(productSchema))
        }

        cart.items.forEach { product ->
            cart.subtotal = +product.quantity * product.price
            cart.shipping = +product.quantity + product.shipping
            cart.tax = +(product.quantity * product.price) * product.tax
        }

        cart.total = cart.subtotal + cart.shipping + cart.tax

        return cart
    }

}