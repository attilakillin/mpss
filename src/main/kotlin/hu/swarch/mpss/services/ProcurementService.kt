package hu.swarch.mpss.services

import hu.swarch.mpss.dal.ProductionGoalRepository
import hu.swarch.mpss.entities.Part
import hu.swarch.mpss.entities.ProductionGoal
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class ProcurementService (
    private val productionGoalRepository: ProductionGoalRepository
) {
    fun details(): MutableMap<ProductionGoal, MutableMap<Part, Duration>> {
        val productionGoals = productionGoalRepository.findAll()
        val procurementMap: MutableMap<ProductionGoal, MutableMap<Part, Duration>> = mutableMapOf()
        for (pg in productionGoals) {
            val prodProcurement: MutableMap<Part, Duration> = mutableMapOf()
            for (p in pg.products.entries)
                prodProcurement[p.key] = p.key.calculateSumDuration()

            procurementMap[pg] = prodProcurement
        }
        return procurementMap
    }
}