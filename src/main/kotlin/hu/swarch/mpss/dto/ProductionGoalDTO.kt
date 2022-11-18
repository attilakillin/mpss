package hu.swarch.mpss.dto

import hu.swarch.mpss.entities.ProductionGoal
import java.time.LocalDate
import java.time.ZoneId

data class ProductionGoalDTO (
    val id: Long?,
    val deadline_time: String,
    val product_ids: List<Long>?
)

fun ProductionGoal.toDTO(): ProductionGoalDTO {
    return ProductionGoalDTO(id, deadline.toString(), products.map { it.id })
}

fun ProductionGoalDTO.toEntity(): ProductionGoal? {
    val date: LocalDate = LocalDate.parse(deadline_time)
    val instant = date.atStartOfDay(ZoneId.of("Europe/Budapest")).toInstant()
    if (product_ids == null)
        return ProductionGoal(0, listOf(), instant)

    return ProductionGoal(0, listOf(), instant)
}

fun ProductionGoalDTO.toEntityWithId(): ProductionGoal? {
    if (id == null) return null

    return toEntity()?.also { it.id = id } ?: return null
}