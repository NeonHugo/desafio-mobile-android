package com.nm.data.mapper

import com.nm.data.model.ProductSchema
import com.nm.domain.entity.Product
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProductSchemaToProductMapperTest {
    private lateinit var mapper: ProductSchemaToProductMapper

    @Before
    fun setupTest() {
        mapper = ProductSchemaToProductMapper()
    }

    @Test
    fun ProductSchemaToProductMapperTest() {
        val product = mapper.transform(
            createProductSchema()
        )

        assertEquals(createProduct(), product)
    }

    private fun createProductSchema(): ProductSchema {
        return ProductSchema(
            name = "Pencil",
            quantity = 1,
            stock = 5,
            image_url = "https://github.com/charleston10/test-android-nexaas/blob/master/assets/pencil.png?raw=true",
            price = 150,
            tax = 162,
            shipping = 50,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odioc."
        )
    }

    private fun createProduct(): Product {
        return Product(
            name = "Pencil",
            quantity = 1,
            stock = 5,
            image_url = "https://github.com/charleston10/test-android-nexaas/blob/master/assets/pencil.png?raw=true",
            price = 1.50,
            tax = 1.62,
            shipping = 0.5,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odioc."
        )
    }

    @After
    fun tearDown() {
        //
    }

}