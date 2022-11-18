package hu.swarch.mpss.dto

import hu.swarch.mpss.entities.BasicPart
import hu.swarch.mpss.entities.Product
import java.time.Duration

data class ProductDTO (
    val id: Long?,
    val name: String,
    // TODO list of PARTs
)

fun Product.toDTO(): ProductDTO {
    return ProductDTO(id, name)
}

fun ProductDTO.toEntity(): Product? {
    if (name.isBlank()) return null

    return Product(0, name, BasicPart("demo", 0.0, Duration.ZERO))
}