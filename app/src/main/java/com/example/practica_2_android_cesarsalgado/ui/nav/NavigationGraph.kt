package com.example.practica_2_android_cesarsalgado.ui.nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase
import com.example.practica_2_android_cesarsalgado.ui.activities.AdminOptionsActivity
import com.example.practica_2_android_cesarsalgado.ui.activities.AuthenticationActivity
import com.example.practica_2_android_cesarsalgado.ui.activities.FirstActivity
import com.example.practica_2_android_cesarsalgado.ui.activities.LoginActivity
import com.example.practica_2_android_cesarsalgado.ui.activities.PlayerOptionsActivity
import com.example.practica_2_android_cesarsalgado.ui.activities.QuestionActivity
import com.example.practica_2_android_cesarsalgado.ui.activities.QuestionAddActivity
import com.example.practica_2_android_cesarsalgado.ui.activities.QuestionDeleteActivity
import com.example.practica_2_android_cesarsalgado.ui.activities.RankingActivity
import com.example.practica_2_android_cesarsalgado.ui.activities.RegisterActivity
import com.example.practica_2_android_cesarsalgado.ui.activities.StatsActivity

@Composable
fun NavigationGraph(appDatabase: AppDatabase, applicationContext: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Paths.FirstActivity.path) {
        composable(Paths.FirstActivity.path) {
            FirstActivity(navController)
        }
        composable(Paths.AuthenticationActivity.path) {
            AuthenticationActivity(navController)
        }
        composable(Paths.LoginActivity.path) {
            LoginActivity(navController, appDatabase, applicationContext)
        }
        composable(Paths.RegisterActivity.path) {
            RegisterActivity(navController, appDatabase, applicationContext)
        }
        composable(Paths.QuestionActivity.path + "/{mode}") {
            val mode = it.arguments?.getString("mode")
            val userName = it.arguments?.getString("userName")
            appDatabase.getUserDao().updateGamesPlayed(userName)
            QuestionActivity(navController, appDatabase, applicationContext, mode, userName)
        }
        composable(Paths.PlayerOptionsActivity.path + "/{userName}") {
            val userName = it.arguments?.getString("userName")
            PlayerOptionsActivity(navController, appDatabase, applicationContext, userName)
        }
        composable(Paths.AdminOptionsActivity.path + "/{userName}") {
            val userName = it.arguments?.getString("userName")
            AdminOptionsActivity(navController, appDatabase, applicationContext, userName)
        }
        composable(Paths.QuestionAddActivity.path) {
            QuestionAddActivity(navController, appDatabase, applicationContext)
        }
        composable(Paths.QuestionDeleteActivity.path) {
            QuestionDeleteActivity(navController, appDatabase, applicationContext)
        }
        composable(Paths.RankingActivity.path) {
            RankingActivity(appDatabase, navController)
        }
        composable(Paths.StatsActivity.path + "/{correctAnswers}") {
            val correctAnswers = it.arguments?.getString("correctAnswers")
            StatsActivity(navController, appDatabase, correctAnswers)
        }
    }
}