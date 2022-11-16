package hu.swarch.mpss.dto

import java.time.Duration

fun Duration.prettyPrint(): String {
    val days = toDaysPart()
    val hours = toHoursPart()
    val minutes = toMinutesPart()
    val seconds = toSecondsPart()

    val result = StringBuilder()
    if (days > 0) result.append("${days}d ")
    if (hours > 0) result.append("${hours}h ")
    if (minutes > 0) result.append("${minutes}m ")
    if (seconds > 0) result.append("${seconds}s ")

    return result.trim().toString()
}

fun String.toDuration(): Duration? {
    val result = Duration.ZERO

    val parts = split(' ')
    for (part in parts) {
        if (part.endsWith('d')) {
            val days = part.substringBefore('d').toLongOrNull() ?: return null
            result.plusDays(days)
        }
        if (part.endsWith('h')) {
            val hours = part.substringBefore('h').toLongOrNull() ?: return null
            result.plusHours(hours)
        }
        if (part.endsWith('m')) {
            val minutes = part.substringBefore('m').toLongOrNull() ?: return null
            result.plusMinutes(minutes)
        }
        if (part.endsWith('s')) {
            val seconds = part.substringBefore('s').toLongOrNull() ?: return null
            result.plusSeconds(seconds)
        }
    }

    return result
}
