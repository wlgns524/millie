package com.subject.millie.util

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

const val ZoneSeoul = "Asia/Seoul"
const val PATTERN = "yyyy-MM-dd HH:mm:ss SSS"
fun Instant.toLocalDateTime(): LocalDateTime {
    return toLocalDateTime(TimeZone.of(ZoneSeoul))
}

fun LocalDateTime.toFormattedString(): String {
    return this.toJavaLocalDateTime().format(DateTimeFormatter.ofPattern(PATTERN))
}