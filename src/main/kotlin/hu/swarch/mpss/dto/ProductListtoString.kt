package hu.swarch.mpss.dto

import hu.swarch.mpss.entities.Part
import hu.swarch.mpss.entities.Product

fun List<Product>.prettyPrint(): String {
    val builder = StringBuilder()
    for (e in toList()) {
        builder.append("${e.id}=${e.name},")
    }
    return builder.toString().removeSuffix(",")
}