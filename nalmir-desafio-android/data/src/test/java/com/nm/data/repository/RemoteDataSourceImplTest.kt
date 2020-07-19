package com.nm.data.repository

import com.nm.data.mapper.ProductSchemaListToCartMapper
import com.nm.data.mapper.ProductSchemaToProductMapper
import com.nm.domain.entity.Cart
import com.nm.domain.entity.Product
import com.nm.infra.net.ApiError
import com.nm.infra.net.ErrorResults
import com.nm.infra.net.Results
import com.nm.infra.net.SuccessResults
import com.nm.infra.net.data.RetrofitBuild
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {

    private var mockWebServer: MockWebServer? = null

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setupTest() {
        startMockWebServer()

        remoteDataSource = RemoteDataSourceImpl(
            RetrofitBuild.makeService(mockWebServer!!.url("/")),
            ProductSchemaListToCartMapper(
                ProductSchemaToProductMapper()
            )
        )
    }

    @Test
    fun remoteDataSourceSuccess() = runBlocking {
        mockWebServer!!.enqueue(
            MockResponse().apply {
                val response = MockResponseFileReader(ESTABLISHMENT_LIST_SUCCESS).content
                setResponseCode(HTTP_OK).setBody(response)
            }
        )

        val res = remoteDataSource.getCart()

        assertEquals(createSuccessfullResponse(), res)
    }

    private fun createSuccessfullResponse(): Results<Cart> {
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

        return SuccessResults(
            Cart(
                total = 5.43,
                subtotal = 1.5,
                shipping = 1.5,
                tax = 2.43,
                items = items
            )
        )
    }

    @Test
    fun remoteDataSourceUnSuccess() = runBlocking {
        mockWebServer!!.enqueue(
            MockResponse().apply {
                val response = createWSResponseError()
                setResponseCode(HTTP_ERROR).setBody(response)
            }
        )

        val res = remoteDataSource.getCart()

        assertEquals(createErrorResponse(), res)
    }

    private fun createErrorResponse(): Results<Cart> {
        return ErrorResults(ApiError(
            errorCode = "400",
          errorMessage = "Client Error"
        ))
    }

    private fun createWSResponseError(): String {
        return ""
    }

    @After
    fun tearDown() {
        stopMockWebServer()
    }

    private fun startMockWebServer() {
        if (mockWebServer == null) {
            mockWebServer = MockWebServer()
            mockWebServer?.start(MOCK_WEB_SERVER_PORT)
        }
    }

    private fun stopMockWebServer() {
        if (mockWebServer != null) {
            mockWebServer?.shutdown()
            mockWebServer = null
        }
    }

    companion object {
        const val MOCK_WEB_SERVER_PORT = 9091

        const val HTTP_OK = 200
        const val HTTP_ERROR = 400

        const val ESTABLISHMENT_LIST_SUCCESS = "data-success.json"
    }


}