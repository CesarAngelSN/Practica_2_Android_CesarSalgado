package com.example.practica_2_android_cesarsalgado.ui.nav

sealed class Paths(val path: String) {
    object FirstActivity : Paths("firstactivity")
    object AuthenticationActivity : Paths("authenticationactivity")
    object LoginActivity : Paths("loginactivity")
    object RegisterActivity : Paths("registeractivity")
    object QuestionActivity : Paths("questionactivity")
    object PlayerOptionsActivity : Paths("playeroptionsactivity")
    object AdminOptionsActivity : Paths("adminoptionsactivity")
    object QuestionAddActivity : Paths("questionaddactivity")
    object QuestionDeleteActivity : Paths("questiondeleteactivity")
    object RankingActivity : Paths("rankingactivity")
}
