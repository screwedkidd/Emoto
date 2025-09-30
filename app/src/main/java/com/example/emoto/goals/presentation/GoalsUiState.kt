package com.example.emoto.goals.presentation

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.emoto.goals.data.GoalsDataSource
import com.example.emoto.goals.model.Goal

data class GoalsUiState(
    val goals: SnapshotStateList<Goal> = GoalsDataSource.goals.toMutableStateList(),
    val searchBarState: TextFieldState = TextFieldState()
)