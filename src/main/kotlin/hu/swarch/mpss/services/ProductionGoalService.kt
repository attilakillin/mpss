package hu.swarch.mpss.services

import hu.swarch.mpss.dal.ProductionGoalRepository
import org.springframework.stereotype.Service

@Service
class ProductionGoalService(
    private val productionGoalRepository: ProductionGoalRepository
) {

}