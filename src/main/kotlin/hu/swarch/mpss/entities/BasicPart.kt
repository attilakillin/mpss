package hu.swarch.mpss.entities

import hu.swarch.mpss.dto.prettyPrint
import java.time.Duration
import javax.persistence.Entity

@Entity
class BasicPart(
    name: String,
    val price: Double,
    val procurementTime: Duration
) : Part(name = name) {
    val procurementTimeAsString = procurementTime.prettyPrint()
    override fun calculateSumDuration(): Duration {
        return procurementTime
    }
}
