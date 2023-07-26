package com.junclabs

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val task: String
)