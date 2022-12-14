package hu.swarch.mpss.dto

import hu.swarch.mpss.entities.Part

fun<V> Map<Part, V>.prettyPrint(): String {
    val builder = StringBuilder()

    for ((key, value) in entries) {
        builder.append("${key.id}=${key.name}=${value},")
    }

    return builder.toString().removeSuffix(",")
}
