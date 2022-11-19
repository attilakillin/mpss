package hu.swarch.mpss.dto

import hu.swarch.mpss.entities.BasicPart
import hu.swarch.mpss.entities.Product
import java.time.Duration

data class ProductDTO (
    val id: Long?,
    val name: String,
    val mainPart_id: Long
)

fun Product.toDTO(): ProductDTO {
    return ProductDTO(id, name, mainPart.id)
}