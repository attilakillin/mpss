package hu.swarch.mpss.entities

import java.time.Duration
import javax.persistence.CollectionTable
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.OneToMany

@Entity
class ComplexPart(
    name: String,
    @ElementCollection
    @JoinTable(name = "complex_part_subparts", joinColumns = [JoinColumn(name = "part_id", referencedColumnName = "id")])
    val subparts: Map<Part, Int>,
    val constructionTime: Duration
) : Part(name = name)
