package hu.swarch.mpss.services

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dal.ProductionGoalRepository
import hu.swarch.mpss.dto.ProductionGoalDTO
import hu.swarch.mpss.entities.ComplexPart
import hu.swarch.mpss.entities.Part
import hu.swarch.mpss.entities.ProductionGoal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId

@Service
class ProductionGoalService(
    private val productionGoalRepository: ProductionGoalRepository,
    private val partRepository: PartRepository
) {
    private fun dtoToEntity(dto: ProductionGoalDTO): ProductionGoal? {
        if (dto.products.isEmpty()) return null

        val date: LocalDate = try { LocalDate.parse(dto.deadline_time) } catch (e: Exception) { return null }
        val instant = date.atStartOfDay(ZoneId.of("Europe/Budapest")).toInstant()

        val products: MutableMap<Part, Int> = mutableMapOf()
        for ((productId, count) in dto.products) {
            if (count == 0) return null
            val product = partRepository.findByIdOrNull(productId) ?: return null
            if (product !is ComplexPart || !product.isFinalProduct) return null

            products[product] = count
        }

        return ProductionGoal(
            id = 0,
            products = products,
            deadline = instant
        )
    }

    fun findAllProductionGoals(): List<ProductionGoal> {
        return productionGoalRepository.findAll()
    }

    fun saveProductionGoal(dto: ProductionGoalDTO): ProductionGoal? {
        if (dto.id != null) return null

        val productionGoal = dtoToEntity(dto) ?: return null
        return productionGoalRepository.save(productionGoal)
    }
    fun updateProductionGoal(dto: ProductionGoalDTO): ProductionGoal? {
        if (dto.id == null || !productionGoalRepository.existsById(dto.id)) return null

        val productionGoal = dtoToEntity(dto)?.also { it.id = dto.id } ?: return null
        return productionGoalRepository.save(productionGoal)
    }

    fun deleteProductionGoal(id: Long) {
        productionGoalRepository.deleteById(id)
    }
}
