package com.nm.data.mapper

import com.nm.data.model.ProductSchema
import com.nm.domain.entity.Product
import com.nm.infra.extensions.toSafeMoney
import com.nm.infra.net.data.Mapper

class ProductSchemaToProductMapper : Mapper<ProductSchema, Product>() {
    override fun transform(item: ProductSchema): Product {
        return Product(
            description = item.description,
            image_url = item.image_url,
            name = item.name,
            price = item.price.toSafeMoney(),
            quantity = item.quantity,
            shipping = item.shipping.toSafeMoney(),
            stock = item.stock,
            tax = item.tax.toSafeMoney()
        )
    }
}