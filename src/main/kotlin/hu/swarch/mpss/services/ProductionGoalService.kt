package hu.swarch.mpss.services

import hu.swarch.mpss.dal.ProductRepository
import hu.swarch.mpss.dal.ProductionGoalRepository
import hu.swarch.mpss.dto.ProductionGoalDTO
import hu.swarch.mpss.entities.Product
import hu.swarch.mpss.entities.ProductionGoal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId

@Service
class ProductionGoalService(
    private val productionGoalRepository: ProductionGoalRepository,
    private val productRepository: ProductRepository
) {
    private fun dtoToEntity(productionGoalDTO: ProductionGoalDTO): ProductionGoal {
        val date: LocalDate = LocalDate.parse(productionGoalDTO.deadline_time)
        val instant = date.atStartOfDay(ZoneId.of("Europe/Budapest")).toInstant()
        if (productionGoalDTO.product_ids == null)
            return ProductionGoal(0, listOf(), instant)
        val products: List<Product> = productionGoalDTO.product_ids.map {
            productRepository.findByIdOrNull(it) ?: throw EntityDoesntExists()
        }
        return ProductionGoal(0, products, instant)
    }
    fun createProductionGoal(productionGoalDTO: ProductionGoalDTO) {
        productionGoalRepository.save(dtoToEntity(productionGoalDTO))
    }
    fun updateProductionGoal(productionGoalDTO: ProductionGoalDTO) {
        if (productionGoalDTO.id == null)
            throw IdCannotBeNullException()
        if (!productionGoalRepository.existsById(productionGoalDTO.id))
            throw EntityDoesntExists()
        productionGoalRepository.save(dtoToEntity(productionGoalDTO).also { it.id = productionGoalDTO.id })
    }
}