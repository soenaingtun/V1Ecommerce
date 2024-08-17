package com.testing.visibleoneecommerce.repository

import com.testing.visibleoneecommerce.model.ProductResponse
import com.testing.visibleoneecommerce.network.ProductApiService
import javax.inject.Inject

class ProductRepositoryImpl
@Inject constructor(private val productApi: ProductApiService):ProductRepository {
    override suspend fun getProducts(): List<ProductResponse> = productApi.getProducts();
}