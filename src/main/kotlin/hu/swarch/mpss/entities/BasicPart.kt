package hu.swarch.mpss.entities

import java.time.Duration

data class BasicPart(
    val name: String,
    val price: Double,
    val procurementTime: Duration
) : Part
