package hu.swarch.mpss.dto

import hu.swarch.mpss.entities.ProductionGoal

data class ProductionGoalDTO (
    val id: Long?,
    val deadline_time: String,
    val product_ids: List<Long>
)

fun ProductionGoal.toDTO(): ProductionGoalDTO {
    return ProductionGoalDTO(id, deadline.toString(), products.map { it.id })
}