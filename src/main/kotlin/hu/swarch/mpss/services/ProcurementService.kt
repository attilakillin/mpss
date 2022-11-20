package hu.swarch.mpss.services

import hu.swarch.mpss.dal.ProductionGoalRepository
import hu.swarch.mpss.dto.prettyPrint
import hu.swarch.mpss.entities.Part
import hu.swarch.mpss.entities.ProductionGoal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class ProcurementService (
    private val productionGoalRepository: ProductionGoalRepository
) {
    fun allSubParts(id: Long): MutableMap<Part, String>? {
        val productionGoal: ProductionGoal = productionGoalRepository.findByIdOrNull(id) ?: return null
        val procurementMap: MutableMap<Part, String> = mutableMapOf()
        for (p in productionGoal.products.entries)
            procurementMap.putAll(p.key.getAllSubParts())
        return procurementMap
    }
    fun details(): MutableMap<ProductionGoal, String> {
        val productionGoals = productionGoalRepository.findAll()
        val procurementMap: MutableMap<ProductionGoal, String> = mutableMapOf()
        for (pg in productionGoals) {
            var max = Duration.ZERO
            for (p in pg.products.entries) {
                val dur = p.key.calculateSumDuration()
                if (max < dur)
                    max = dur
            }
            procurementMap[pg] = max.prettyPrint()
        }
        return procurementMap
    }
}