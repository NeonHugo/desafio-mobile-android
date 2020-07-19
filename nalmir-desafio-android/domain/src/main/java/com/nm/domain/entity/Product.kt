package com.nm.domain.entity

data class Product(
    val description: String,
    val image_url: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val shipping: Double,
    val stock: Int,
    val tax: Double
)