package com.nm.data.mapper

import com.nm.data.model.ProductSchema
import com.nm.domain.entity.Cart
import com.nm.domain.entity.Product
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProductSchemaListToCartMapperTest {

    private lateinit var mapper: ProductSchemaListToCartMapper

    @Before
    fun setupTest() {
        mapper = ProductSchemaListToCartMapper(
            ProductSchemaToProductMapper()
        )
    }

    @Test
    fun ProductSchemaListToCartMapperTest() {
        val product = mapper.transform(
            createProductSchemaList()
        )

        assertEquals(createCart(), product)
    }

    private fun createProductSchemaList(): List<ProductSchema> {
        val products = arrayListOf<ProductSchema>()

        products.add(
            ProductSchema(
                name = "Pencil",
                quantity = 1,
                stock = 5,
                image_url = "https://github.com/charleston10/test-android-nexaas/blob/master/assets/pencil.png?raw=true",
                price = 150,
                tax = 162,
                shipping = 50,
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odioc."
            )
        )

        return products
    }

    private fun createCart(): Cart {
        val items = ArrayList<Product>()
        items.add(
            Product(
                name = "Pencil",
                quantity = 1,
                stock = 5,
                image_url = "https://github.com/charleston10/test-android-nexaas/blob/master/assets/pencil.png?raw=true",
                price = 1.50,
                tax = 1.62,
                shipping = 0.5,
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odioc."
            )
        )

        val cart = Cart(
            total = 5.43,
            subtotal = 1.5,
            shipping = 1.5,
            tax = 2.43,
            items = items
        )
        return cart
    }

    @After
    fun tearDown() {
        //
    }


}