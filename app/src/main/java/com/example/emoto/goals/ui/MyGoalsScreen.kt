package com.example.emoto.goals.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emoto.R
import com.example.emoto.app.ui.components.CustomTextField
import com.example.emoto.app.ui.theme.EmotoTheme
import com.example.emoto.goals.data.GoalsDataSource
import com.example.emoto.goals.model.Goal
import com.example.emoto.goals.presentation.GoalsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyGoalsScreen(
    viewModel: GoalsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        MyGoalsTopBar()
        Spacer(Modifier.size(16.dp))
        GoalsSearchBar(uiState.searchBarState)
        Spacer(Modifier.size(16.dp))
        GoalsList(viewModel)
    }
}

@Composable
fun GoalsSearchBar(state: TextFieldState) {
    CustomTextField(
        state = state,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.offset(y = 1.dp).size(20.dp)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Mic,
                contentDescription = null,
                modifier = Modifier.offset(y = 1.dp).size(20.dp)
            )
        },
        placeholderText = stringResource(R.string.search),
        placeholderStyle = MaterialTheme.typography.bodyLarge,
        lineLimits = TextFieldLineLimits.SingleLine
    )
}

@Composable
fun MyGoalsTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.menu_icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = stringResource(R.string.my_goals),
            style = MaterialTheme.typography.headlineLarge
        )
        IconButton(
            onClick = {},
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun GoalsList(
    viewModel: GoalsViewModel,
    modifier: Modifier = Modifier
) {
    val currentGoals = viewModel.getCurrentGoals()
    val completedGoals = viewModel.getCompletedGoals()

    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = stringResource(R.string.current),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        items(currentGoals) {
            GoalItem(
                goal = it,
                viewModel = viewModel,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        
        item {
            Text(
                text = stringResource(R.string.completed),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
            )
        }
        
        items(completedGoals) {
            GoalItem(
                goal = it,
                viewModel = viewModel,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun GoalItem(
    goal: Goal,
    viewModel: GoalsViewModel,
    modifier: Modifier = Modifier
) {
    val pair = getDeadlineStringAndColor(goal, viewModel)
    val deadlineLabel: String = pair.first
    val cardColor: Color = pair.second
    val textColor: Color =
        if (goal.isCompleted)
            colorResource(R.color.white)
        else
            colorResource(R.color.black)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
            contentColor = textColor
        ),
        shape = MaterialTheme.shapes.medium,
        modifier =
            if (goal.isCompleted && goal.isExpired())
                modifier.graphicsLayer(alpha = 0.6f)
            else
                modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                //Spacer(Modifier.size(8.dp))
                Text(
                    text = goal.name,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.size(4.dp))
                Text(
                    text = deadlineLabel,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.size(16.dp))
            Checkbox(
                checked = goal.isCompleted,
                onCheckedChange = { viewModel.toggleGoalCompleted(goal.id) },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = cardColor,
                    checkedColor = colorResource(R.color.white),
                    uncheckedColor = colorResource(R.color.black)
                ),
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
private fun getDeadlineStringAndColor(goal: Goal, viewModel: GoalsViewModel): Pair<String, Color> {
    val deadlineLabel: String
    val color: Color

    if (!goal.isExpired() && goal.isCompleted) {
        deadlineLabel = viewModel.getDaysLeftString(goal)
        color = colorResource(R.color.tea_green)
    }
    else if (!goal.isExpired() && !goal.isCompleted) {
        deadlineLabel = viewModel.getDaysLeftString(goal)
        color = colorResource(R.color.milky_matcha)
    }
    else if (goal.isCompleted) {
        deadlineLabel = stringResource(R.string.expired)
        color = colorResource(R.color.tea_green)
    }
    else {
        deadlineLabel = stringResource(R.string.change_deadline)
        color = colorResource(R.color.pastel_pink)
    }

    return Pair(deadlineLabel, color)
}

@Preview
@Composable
fun GoalItemPreview() {
    EmotoTheme {
        GoalItem(GoalsDataSource.goals.first(), viewModel())
    }
}

@Preview
@Composable
fun GoalsListPreview() {
    EmotoTheme {
        GoalsList(viewModel())
    }
}

@Preview
@Composable
fun MyGoalsScreenPreview() {
    EmotoTheme {
        Surface {
            MyGoalsScreen()
        }
    }
}