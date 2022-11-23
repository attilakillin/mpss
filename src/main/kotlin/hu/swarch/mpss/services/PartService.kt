package hu.swarch.mpss.services

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.entities.BasicPart
import hu.swarch.mpss.entities.ComplexPart
import hu.swarch.mpss.entities.Part
import org.springframework.stereotype.Service

@Service
class PartService(
    private val repository: PartRepository
) {
    fun findAllIntermediateParts(): List<Part> {
        val results = mutableListOf<Part>()
        results.addAll(repository.findAllBasicParts())
        results.addAll(repository.findAllNonFinalComplexParts())
        return results
    }

    fun getProcurementToCompletionDurations(parts: Map<Part, Int>): MutableMap<Part, PartProcurementData> {
        val result = mutableMapOf<Part, PartProcurementData>()

        for ((subpart, count) in parts) {
            val subResult = getSubpartCompletionDuration(subpart)

            for ((rootPart, data) in subResult) {
                val dataSoFar = result[rootPart]

                val earlier = if (dataSoFar != null && dataSoFar.deadlineBeforeFinish >= data.deadlineBeforeFinish) {
                    dataSoFar.deadlineBeforeFinish
                } else {
                    data.deadlineBeforeFinish
                }

                result[rootPart] = PartProcurementData((dataSoFar?.count ?: 0) + count * data.count, earlier)
            }
        }

        return result
    }

    fun getSubpartCompletionDuration(part: Part): MutableMap<Part, PartProcurementData> {
        return when (part) {
            is BasicPart -> mutableMapOf(part to PartProcurementData(1, part.procurementTime))
            is ComplexPart -> {
                return getProcurementToCompletionDurations(part.subparts).mapValues { (_, data) ->
                    PartProcurementData(data.count, data.deadlineBeforeFinish.plus(part.constructionTime))
                }.toMutableMap()
            }
            else -> throw IllegalStateException("Found invalid part type!")
        }
    }
}
