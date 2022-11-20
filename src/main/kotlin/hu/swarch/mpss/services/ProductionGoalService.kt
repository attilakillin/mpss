package hu.swarch.mpss.services

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dal.ProductionGoalRepository
import hu.swarch.mpss.dto.ProductionGoalDTO
import hu.swarch.mpss.entities.ComplexPart
import hu.swarch.mpss.entities.Part
import hu.swarch.mpss.entities.ProductionGoal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ProductionGoalService(
    private val productionGoalRepository: ProductionGoalRepository,
    private val partRepository: PartRepository
) {
    private fun dtoToEntity(dto: ProductionGoalDTO): ProductionGoal? {
        if (dto.products.isEmpty()) return null

        val deadline = try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            LocalDateTime.parse(dto.deadline_time, formatter)
        } catch (e: Exception) { return null }

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
            deadline = deadline
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
