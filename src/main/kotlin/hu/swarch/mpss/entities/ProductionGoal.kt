package hu.swarch.mpss.entities

import hu.swarch.mpss.dto.prettyPrint
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Entity(name = "production_goals")
class ProductionGoal(
    @Id @GeneratedValue
    var id: Long = 0,
    @OneToMany @CollectionTable(name = "production_goal_products")
    val products: List<Product>,
    val deadline: Instant
) {
    val getProductsAsString = products.prettyPrint()
    val getDeadlineAsString = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("Europe/Budapest")).format(deadline)
}
