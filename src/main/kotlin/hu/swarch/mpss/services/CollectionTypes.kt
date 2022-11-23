package hu.swarch.mpss.services

import hu.swarch.mpss.dto.prettyPrint
import hu.swarch.mpss.entities.Part
import hu.swarch.mpss.entities.ProductionGoal
import java.time.Duration
import java.time.LocalDateTime

data class PartProcurementData(
    val count: Int,
    val deadlineBeforeFinish: Duration
)

data class ProductProcurementData(
    val targetGoal: ProductionGoal,
    val part: Part,
    val count: Int,
    val deadline: LocalDateTime
) {
    val deadlineAsString = deadline.prettyPrint()
}
