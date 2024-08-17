package com.testing.visibleoneecommerce.model

data class ProductResponse(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingResponse
)

data class RatingResponse(
    val rate: Double,
    val count: Int
)

// Domain Model
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

data class Rating(
    val rate: Double,
    val count: Int
)

fun ProductResponse.toDomainModel(): Product {
    return Product(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image,
        rating = this.rating.toDomainModel()
    )
}

fun RatingResponse.toDomainModel(): Rating {
    return Rating(rate = this.rate, count = this.count)
}
