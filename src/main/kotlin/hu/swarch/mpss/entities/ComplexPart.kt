package hu.swarch.mpss.entities

import java.time.Duration
import javax.persistence.CollectionTable
import javax.persistence.ElementCollection
import javax.persistence.Entity

@Entity(name = "complex_parts")
class ComplexPart(
    id: Long,
    name: String,
    @ElementCollection @CollectionTable(name = "complex_part_subparts")
    val subparts: List<Part>,
    val constructionTime: Duration
) : Part(id, name)
