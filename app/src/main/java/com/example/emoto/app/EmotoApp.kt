package com.example.emoto.app

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.emoto.R
import com.example.emoto.goals.ui.MyGoalsScreen

@Composable
fun EmotoApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold {
        NavHost(
            navController = navController,
            startDestination = AppSection.Goals.name,
            modifier = Modifier.padding(it)
        ) {
            composable(route = AppSection.Goals.name) {
                MyGoalsScreen()
            }
        }
    }
}

enum class AppSection(@StringRes title: Int) {
    Diary(title = R.string.diary),
    Goals(title = R.string.goals),
    Medicine(title = R.string.medicine)
}