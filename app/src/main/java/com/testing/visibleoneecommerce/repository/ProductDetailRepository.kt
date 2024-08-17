package com.testing.visibleoneecommerce.repository

import com.testing.visibleoneecommerce.model.ProductResponse

interface ProductDetailRepository {

    suspend fun getProductDetail(productId: String): ProductResponse
}