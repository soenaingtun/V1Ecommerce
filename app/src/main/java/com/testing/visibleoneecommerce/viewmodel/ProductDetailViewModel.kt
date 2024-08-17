package com.testing.visibleoneecommerce.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.visibleoneecommerce.domain.GetProductDetailUseCase
import com.testing.visibleoneecommerce.domain.GetProductListUseCase
import com.testing.visibleoneecommerce.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel
@Inject constructor(
    private val productDetailUseCase: GetProductDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _productDetailUiState = mutableStateOf(ProductDetailStateOutput())
    val productDetailUiState: State<ProductDetailStateOutput> = _productDetailUiState

    fun getProductDetail(productId: String) {

        productDetailUseCase.invoke(productId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _productDetailUiState.value = ProductDetailStateOutput(data = result.data)

                }
                is Resource.Error -> {
                    _productDetailUiState.value = ProductDetailStateOutput(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _productDetailUiState.value = ProductDetailStateOutput(isLoading = true)
                }
                else -> { }
            }
        }.launchIn(viewModelScope)
    }


}