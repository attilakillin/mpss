package hu.swarch.mpss.entities

import javax.persistence.*
@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class Part(
    @Id @GeneratedValue
    open val id: Long,
    open val name: String
)
