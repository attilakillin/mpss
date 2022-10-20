package hu.swarch.mpss.entities

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Part(
    @Id @GeneratedValue
    open val id: Long,
    open val name: String
)
