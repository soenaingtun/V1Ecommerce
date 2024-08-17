package com.testing.visibleoneecommerce.repository

import com.testing.visibleoneecommerce.model.ProductResponse
import com.testing.visibleoneecommerce.network.ProductApiService
import javax.inject.Inject

class ProductDetailRepositoryImpl
@Inject constructor(private val productApi: ProductApiService):ProductDetailRepository {
    override suspend fun getProductDetail(productId: String): ProductResponse = productApi.getProductDetail(productId)

}