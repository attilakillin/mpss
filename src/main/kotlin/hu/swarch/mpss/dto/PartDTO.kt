package hu.swarch.mpss.dto

import hu.swarch.mpss.entities.BasicPart
import hu.swarch.mpss.entities.ComplexPart

enum class PartType {
    BasicPart, ComplexPart
}

data class PartDTO (

    val id: Long?,
    val type: String?,
    val name: String
)

fun BasicPart.toPartDTO(): PartDTO {
    return PartDTO(id, name, "BasicPart")
}

fun ComplexPart.toPartDTO(): PartDTO {
    return PartDTO(id, name, "ComplexPart")
}
