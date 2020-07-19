package com.nm.domain.usecase

import com.nm.domain.entity.Cart
import com.nm.domain.entity.Product
import com.nm.domain.repository.CartRepository
import com.nm.infra.net.ApiError
import com.nm.infra.net.ErrorResults
import com.nm.infra.net.Results
import com.nm.infra.net.SuccessResults
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CartUseCaseImplTest {

    @Mock
    lateinit var repository: CartRepository

    private lateinit var useCase: CartUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        useCase = CartUseCaseImpl(repository)
    }

    @Test
    fun getCardSuccessful() = runBlocking {
        Mockito.`when`(repository.getCart()).thenReturn(createCard())

        val cart = useCase.showCart()

        assertEquals(createCard(), cart)
    }

    private fun createCard(): Results<Cart> {
        val items = arrayListOf<Product>()
        items.add(
            Product(
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odioc.",
                image_url = "https://github.com/charleston10/test-android-nexaas/blob/master/assets/rubberbands.png?raw=true",
                name = "Pencil",
                price = 1.50,
                quantity = 1,
                shipping = 1.0,
                stock = 5,
                tax = 1.62
            )
        )
        return SuccessResults(
            Cart(
                items = items,
                total = 100.0,
                subtotal = 80.0,
                shipping = 5.0,
                tax = 15.0
            )
        )
    }

    @Test
    fun getCardUnSuccessful() = runBlocking {
        Mockito.`when`(repository.getCart()).thenReturn(createUnSuccessful())

        val cart = useCase.showCart()

        assertEquals(createUnSuccessful(), cart)
    }

    private fun createUnSuccessful(): Results<Cart> {
        return ErrorResults(
            ApiError(
                errorCode = "50",
                errorMessage = "UnSuccessful"
            )
        )
    }

}