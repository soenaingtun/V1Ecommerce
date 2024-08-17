package com.testing.visibleoneecommerce.di

import com.testing.visibleoneecommerce.domain.GetProductDetailUseCase
import com.testing.visibleoneecommerce.domain.GetProductListUseCase
import com.testing.visibleoneecommerce.repository.ProductDetailRepository
import com.testing.visibleoneecommerce.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideProductListUseCases(repository: ProductRepository) =
        GetProductListUseCase(repository)


    @Provides
    fun provideProductDetailUseCases(repository: ProductDetailRepository) =
        GetProductDetailUseCase(repository)

}