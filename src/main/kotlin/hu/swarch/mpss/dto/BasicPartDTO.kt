package hu.swarch.mpss.dto

import hu.swarch.mpss.entities.BasicPart

data class BasicPartDTO(
    val id: Long?,
    val name: String,
    val price: Double,
    val procurementTime: String
)

fun BasicPart.toDTO(): BasicPartDTO {
    return BasicPartDTO(id, name, price, procurementTime.prettyPrint())
}

fun BasicPartDTO.toEntity(): BasicPart? {
    val duration = procurementTime.toDuration() ?: return null
    return BasicPart(name, price, duration)
}

fun BasicPartDTO.toEntityWithId(): BasicPart? {
    if (id == null) return null
    return toEntity()?.also { it.id = id } ?: return null
}
