package hu.swarch.mpss.entities

import java.time.Instant
import javax.persistence.CollectionTable
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "production_goals")
class ProductionGoal(
    @Id @GeneratedValue
    val id: Long,
    @ElementCollection @CollectionTable(name = "production_goal_products")
    val products: List<Product>,
    val deadline: Instant
)
