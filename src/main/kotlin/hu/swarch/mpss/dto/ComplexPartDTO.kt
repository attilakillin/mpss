package hu.swarch.mpss.dto

data class ComplexPartDTO(
    val id: Long?,
    val name: String,
    val subparts: Map<Long, Int>,
    val constructionTime: String,
    val isFinalProduct: Boolean
)
