package hu.swarch.mpss.entities

import hu.swarch.mpss.dto.prettyPrint
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "production_goals")
class ProductionGoal(
    @Id @GeneratedValue
    var id: Long = 0,
    @ElementCollection
    @JoinTable(name = "production_goals_products", joinColumns = [JoinColumn(name = "part_id", referencedColumnName = "id")])
    val products: Map<Part, Int>,
    val deadline: LocalDateTime
) {
    val productsAsString = products.prettyPrint()
    val deadlineAsString = deadline.prettyPrint()
}
