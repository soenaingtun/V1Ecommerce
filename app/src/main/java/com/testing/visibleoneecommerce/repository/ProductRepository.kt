package com.testing.visibleoneecommerce.repository

import com.testing.visibleoneecommerce.model.Product
import com.testing.visibleoneecommerce.model.ProductResponse

interface ProductRepository {

    suspend fun getProducts(): List<ProductResponse>

}