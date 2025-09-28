package com.example.emoto.goals.model

import java.util.Date

data class Goal(
    val name: String,
    val description: String,
    val deadline: Date,
    val isCompleted: Boolean = false
)
