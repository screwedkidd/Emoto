package com.example.emoto.goals.presentation

import androidx.lifecycle.ViewModel
import com.example.emoto.goals.model.Goal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class GoalsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GoalsUiState())
    val uiState = _uiState.asStateFlow()

    fun getDaysLeftString(goal: Goal) : String {
        val daysLeft = ChronoUnit.DAYS.between(
            LocalDate.now(),
            goal.deadline
        ).toInt()

        val daysString =
            if (daysLeft in 10..19 || daysLeft % 10 in 5..9)
                "дней"
            else if (daysLeft % 10 == 1)
                "день"
            else
                "дня"

        return "Истекает через $daysLeft $daysString"
    }

    fun getCurrentGoals(): List<Goal> = _uiState.value.goals
        .filter { !it.isCompleted }
        .sortedBy { it.deadline }
        .filter { it.name.contains(_uiState.value.searchBarState.text, ignoreCase = true) }

    fun getCompletedGoals(): List<Goal> = _uiState.value.goals
        .filter { it.isCompleted }
        .sortedBy { it.deadline }
        .sortedBy { it.isExpired() }
        .filter { it.name.contains(_uiState.value.searchBarState.text, ignoreCase = true) }

    fun toggleGoalCompleted(goalId: Int) {
        val index = _uiState.value.goals.indexOfFirst { it.id == goalId }

        val goal = _uiState.value.goals[index]
        _uiState.value.goals[index] = goal.copy(isCompleted = !goal.isCompleted)
    }
}