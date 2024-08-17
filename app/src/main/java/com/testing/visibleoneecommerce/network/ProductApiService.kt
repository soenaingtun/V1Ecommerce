package com.testing.visibleoneecommerce.network

import com.testing.visibleoneecommerce.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    companion object {
        private const val GET_PRODUCT_LIST = "/products"
        private const val GET_PRODUCT_DETAIL = "/products/{productId}"
    }
    @GET(GET_PRODUCT_LIST)
    suspend fun getProducts(): List<ProductResponse>


    @GET(GET_PRODUCT_DETAIL)
    suspend fun getProductDetail(@Path("productId") productId: String):  ProductResponse

}