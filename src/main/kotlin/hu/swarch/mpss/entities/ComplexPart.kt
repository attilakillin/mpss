package hu.swarch.mpss.entities

import java.time.Duration
import javax.persistence.CollectionTable
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class ComplexPart(
    id: Long,
    name: String,
    @OneToMany @CollectionTable(name = "complex_part_subparts")
    val subparts: Set<Part>,
    val constructionTime: Duration
) : Part(id, name)
