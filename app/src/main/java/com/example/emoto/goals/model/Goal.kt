package com.example.emoto.goals.model

import java.time.LocalDate

data class Goal(
    val id: Int,
    val name: String,
    val description: String,
    val deadline: LocalDate,
    var isCompleted: Boolean = false
) {
    fun isExpired(): Boolean = LocalDate.now() > deadline
}
