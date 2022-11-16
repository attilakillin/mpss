package hu.swarch.mpss.entities

import java.time.Duration
import javax.persistence.Entity

@Entity
class BasicPart(
    name: String,
    val price: Double,
    val procurementTime: Duration
) : Part(name = name)
