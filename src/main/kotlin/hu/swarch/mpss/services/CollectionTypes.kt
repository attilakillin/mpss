package hu.swarch.mpss.services

import hu.swarch.mpss.dto.prettyPrint
import hu.swarch.mpss.entities.Part
import java.time.Duration
import java.time.LocalDateTime

data class PartProcurementData(
    val count: Int,
    val deadlineBeforeFinish: Duration
)

data class ProductProcurementData(
    val part: Part,
    val count: Int,
    val deadline: LocalDateTime
) {
    val deadlineAsString = deadline.prettyPrint()
}
