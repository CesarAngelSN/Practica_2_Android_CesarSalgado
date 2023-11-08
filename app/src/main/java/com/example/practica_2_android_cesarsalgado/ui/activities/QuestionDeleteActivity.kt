package com.example.practica_2_android_cesarsalgado.ui.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionDeleteActivity(navController: NavController, appDatabase: AppDatabase) {
    var registerId by remember {
        mutableStateOf("")
    }
    Column (Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally){
        Column (Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally){
            Text(text = "Introduce the ID of the question you want to remove")
            TextField(value = registerId,
                onValueChange = {
                        text -> registerId = text
                },
                isError = (!registerId.matches(Regex("\\d+"))))
        }
        Row {
            Button(onClick = {
                if (appDatabase.getQuestionDao().get(registerId.toInt()) != null) {
                    println("Existe")
                    appDatabase.getQuestionDao().delete(registerId.toInt())
                }
                else {
                    println("No existe")
                }
                println(appDatabase.getQuestionDao().getAll())
            }) {
                Text(text = "Delete")
            }
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Back")
            }
        }

    }

}