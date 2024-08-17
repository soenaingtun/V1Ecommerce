package com.testing.visibleoneecommerce.domain

import com.testing.visibleoneecommerce.model.ProductResponse
import com.testing.visibleoneecommerce.repository.ProductDetailRepository
import com.testing.visibleoneecommerce.repository.ProductRepository
import com.testing.visibleoneecommerce.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductDetailUseCase
@Inject constructor(
    private val repository: ProductDetailRepository
) {
    operator fun invoke(productId: String): Flow<Resource<ProductResponse>> = flow {
        try {
            emit(Resource.Loading<ProductResponse>())
            val data = repository.getProductDetail(productId)
            emit(Resource.Success<ProductResponse>(data))
        } catch(e: HttpException) {
            emit(Resource.Error<ProductResponse>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<ProductResponse>("Couldn't reach server. Check your internet connection."))
        }
    }
}