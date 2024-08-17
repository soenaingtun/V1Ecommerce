package com.testing.visibleoneecommerce.di

import com.testing.visibleoneecommerce.network.ProductApiService
import com.testing.visibleoneecommerce.repository.ProductDetailRepository
import com.testing.visibleoneecommerce.repository.ProductDetailRepositoryImpl
import com.testing.visibleoneecommerce.repository.ProductRepository
import com.testing.visibleoneecommerce.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideProductListRepository(
        productApi: ProductApiService
    ): ProductRepository =
        ProductRepositoryImpl(productApi = productApi)


    @Provides
    fun provideProductDetailRepository(
        productApi: ProductApiService
    ): ProductDetailRepository =
        ProductDetailRepositoryImpl(productApi = productApi)


}