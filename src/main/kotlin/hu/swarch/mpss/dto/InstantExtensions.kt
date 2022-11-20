package hu.swarch.mpss.dto

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.prettyPrint(): String {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("Europe/Budapest")).format(this)
}
