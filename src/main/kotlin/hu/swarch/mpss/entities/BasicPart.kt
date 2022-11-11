package hu.swarch.mpss.entities

import java.time.Duration
import javax.persistence.Entity

@Entity
class BasicPart(
    id: Long,
    name: String,
    val price: Double,
    val procurementTime: Duration
) : Part(id, name)
