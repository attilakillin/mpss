package hu.swarch.mpss.entities

import java.time.Duration

data class ComplexPart(
    val name: String,
    val subParts: List<Part>,
    val constructionTime: Duration
) : Part
