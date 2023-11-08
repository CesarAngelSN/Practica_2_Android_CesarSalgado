package com.example.practica_2_android_cesarsalgado.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase
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
                        "SQLite_DB").allowMainThreadQueries().build()
                    NavigationGraph(appDatabase, applicationContext)
                }
            }
        }
    }
}