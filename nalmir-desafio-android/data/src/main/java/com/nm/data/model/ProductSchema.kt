package com.nm.data.model

data class ProductSchema(
    val description: String,
    val image_url: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val shipping: Int,
    val stock: Int,
    val tax: Int
)