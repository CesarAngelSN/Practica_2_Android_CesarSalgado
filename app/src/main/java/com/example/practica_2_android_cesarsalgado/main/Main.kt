package com.example.practica_2_android_cesarsalgado.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase
import com.example.practica_2_android_cesarsalgado.data.entity.Question
import com.example.practica_2_android_cesarsalgado.ui.nav.NavigationGraph
import com.example.practica_2_android_cesarsalgado.ui.theme.Practica_2_Android_CesarSalgadoTheme

class Main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practica_2_Android_CesarSalgadoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val appDatabase: AppDatabase = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        "SQLite_DB").fallbackToDestructiveMigration().
                    allowMainThreadQueries().build()
                    if (appDatabase.getQuestionDao().getAll().isEmpty()) {
                        val question1 = Question(0, "What's 11 in binary?", "Selection", "3;11;2;121")
                        val question2 = Question(0, "What's the fourth month of the year?", "Selection", "April;June;May;December")
                        val question3 = Question(0, "Where is the rapper SHO-HAI from?", "Selection", "Zaragoza;Rome;Lugano;Berlin")
                        val question4 = Question(0, "Is Zaragoza the best place?", "True/False", "No;Yes")
                        val question5 = Question(0, "Is it correct to put melon in the migas recipe?", "True/False", "Yes;No")

                        appDatabase.getQuestionDao().insert(question1)
                        appDatabase.getQuestionDao().insert(question2)
                        appDatabase.getQuestionDao().insert(question3)
                        appDatabase.getQuestionDao().insert(question4)
                        appDatabase.getQuestionDao().insert(question5)
                    }
                    NavigationGraph(appDatabase, applicationContext)
                }
            }
        }
    }
}