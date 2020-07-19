package com.nm.data.repository

import com.nm.domain.entity.Cart
import com.nm.domain.repository.CartRepository
import com.nm.infra.net.ApiError
import com.nm.infra.net.ErrorResults
import com.nm.infra.net.Results
import com.nm.infra.net.SuccessResults
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CartRepositoryImplTest {

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    private lateinit var repository: CartRepository

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)
        repository = CartRepositoryImpl(remoteDataSource)
    }

    @Test
    fun showSimulationSuccessfulResponse() = runBlocking {
        Mockito.doReturn(
            createCart()
        ).`when`(remoteDataSource).getCart()

        val response = repository.getCart()

        assertEquals(createCart(), response)
    }

    private fun createCart(): Results<Cart> {
        return SuccessResults(
            Cart()
        )
    }

    @Test
    fun showSimulationUnSuccessfulResponse() = runBlocking {
        Mockito.doReturn(
            createCartFail()
        ).`when`(remoteDataSource).getCart()

        val response = repository.getCart()

        assertEquals(createCartFail(), response)
    }

    private fun createCartFail(): ErrorResults<ApiError> {
        return ErrorResults(ApiError())
    }

}