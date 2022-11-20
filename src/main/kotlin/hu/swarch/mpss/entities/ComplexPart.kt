package hu.swarch.mpss.entities

import hu.swarch.mpss.dto.prettyPrint
import java.time.Duration
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable

@Entity
class ComplexPart(
    name: String,
    @ElementCollection
    @JoinTable(name = "complex_part_subparts", joinColumns = [JoinColumn(name = "part_id", referencedColumnName = "id")])
    val subparts: Map<Part, Int>,
    val constructionTime: Duration,
    val isFinalProduct: Boolean
) : Part(name = name) {
    val constructionTimeAsString = constructionTime.prettyPrint()
    val subpartsAsString = subparts.prettyPrint()
}
