package com.nm.domain.entity

data class Cart(
    var items: ArrayList<Product> = arrayListOf(),
    var total: Double = 0.0,
    var subtotal: Double = 0.0,
    var shipping: Double = 0.0,
    var tax: Double = 0.0
)
