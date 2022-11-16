package hu.swarch.mpss.entities

import java.time.Instant
import javax.persistence.*

@Entity(name = "production_goals")
class ProductionGoal(
    @Id @GeneratedValue
    val id: Long = 0,
    @OneToMany @CollectionTable(name = "production_goal_products")
    val products: List<Product>,
    val deadline: Instant
)
