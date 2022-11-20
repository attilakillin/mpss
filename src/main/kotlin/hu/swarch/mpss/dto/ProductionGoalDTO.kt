package hu.swarch.mpss.dto

data class ProductionGoalDTO (
    val id: Long?,
    val deadline_time: String,
    val products: Map<Long, Int>
)
