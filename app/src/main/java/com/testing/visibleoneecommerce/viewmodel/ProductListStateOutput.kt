package com.testing.visibleoneecommerce.viewmodel

import com.testing.visibleoneecommerce.model.ProductResponse

data class ProductListStateOutput(var isLoading: Boolean = false,
                             val data: List<ProductResponse>? = null,
                             val error: String = "")