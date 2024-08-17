package com.testing.visibleoneecommerce.viewmodel

import com.testing.visibleoneecommerce.model.ProductResponse

data class ProductDetailStateOutput (var isLoading: Boolean = false,
                                val data: ProductResponse? = null,
                                val error: String = "")