package com.example.emoto.goals.data

import androidx.compose.ui.platform.LocalDensity
import com.example.emoto.goals.model.Goal
import java.time.LocalDate

object GoalsDataSource {
    var goals = listOf(
        Goal(0, "Начать регулярно заниматься спортом", "", LocalDate.now().minusDays(1)),
        Goal(1, "Записаться на курсы английского", "", LocalDate.now().plusDays(2)),
        Goal(2, "Похудеть на 3 кг", "", LocalDate.now().plusDays(9)),
        Goal(3, "Закончить проект", "", LocalDate.now().plusDays(22)),
        Goal(4, "Пройти новый курс по психологии", "Я давно хотела изучить курс от моего любимого психолога. Надеюсь, это поможет мне лучше понимать себя и слышать свои чувства.", LocalDate.now().plusDays(57)),
        Goal(5, "Собрать гардероб для поездки", "", LocalDate.now().plusDays(2), true),
        Goal(6, "Попробовать новые дыхательные практики", "", LocalDate.now().plusDays(22), true),
        Goal(7, "Покрасить стены в комнате", "", LocalDate.now().minusDays(1), true),
        Goal(8, "Получить повышение", "", LocalDate.now().minusDays(1), true),
        Goal(9, "Купить абонемент в зал", "", LocalDate.now().minusDays(1), true)
    )
}