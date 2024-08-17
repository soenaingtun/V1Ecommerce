package com.testing.visibleoneecommerce.domain

import com.testing.visibleoneecommerce.model.ProductResponse
import com.testing.visibleoneecommerce.repository.ProductRepository
import com.testing.visibleoneecommerce.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductListUseCase
@Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<ProductResponse>>> = flow {
        try {
            emit(Resource.Loading<List<ProductResponse>>())
            val data = repository.getProducts()
            emit(Resource.Success<List<ProductResponse>>(data))
        } catch(e: HttpException) {
            emit(Resource.Error<List<ProductResponse>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<ProductResponse>>("Couldn't reach server. Check your internet connection."))
        }
    }
}