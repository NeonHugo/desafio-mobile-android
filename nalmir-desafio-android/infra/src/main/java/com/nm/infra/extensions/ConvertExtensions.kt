package com.nm.infra.extensions

fun Int.toSafeMoney(): Double {
    return try {
        (this / 100.0)
    } catch (e: Exception) {
        0.0
    }
}