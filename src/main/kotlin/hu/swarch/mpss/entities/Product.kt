package hu.swarch.mpss.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity(name = "products")
class Product(
    @Id @GeneratedValue
    val id: Long = 0,
    val name: String,
    @OneToOne @JoinColumn(name = "main_part_id")
    val mainPart: Part
)
