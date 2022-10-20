package hu.swarch.mpss.entities

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ProductionGoal(
    @Id val id: Long,
    val products: List<Product>,
    val deadline: Instant
)
