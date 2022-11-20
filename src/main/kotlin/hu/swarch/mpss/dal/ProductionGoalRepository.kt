package hu.swarch.mpss.dal

import hu.swarch.mpss.entities.ProductionGoal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductionGoalRepository : JpaRepository<ProductionGoal, Long>
