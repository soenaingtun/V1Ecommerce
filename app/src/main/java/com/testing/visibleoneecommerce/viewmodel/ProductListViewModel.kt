package com.testing.visibleoneecommerce.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.visibleoneecommerce.domain.GetProductListUseCase
import com.testing.visibleoneecommerce.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel
@Inject constructor(
    private val productListUseCase: GetProductListUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _productListUiState = mutableStateOf(ProductListStateOutput())
    val productListUiState: State<ProductListStateOutput> = _productListUiState

    fun getProductList() {

        productListUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _productListUiState.value = ProductListStateOutput(data = result.data)

                }
                is Resource.Error -> {
                    _productListUiState.value = ProductListStateOutput(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _productListUiState.value = ProductListStateOutput(isLoading = true)
                }
                else -> { }
            }
        }.launchIn(viewModelScope)
    }


}