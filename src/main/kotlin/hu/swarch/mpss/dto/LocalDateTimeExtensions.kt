package hu.swarch.mpss.dto

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.prettyPrint(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return format(formatter)
}
