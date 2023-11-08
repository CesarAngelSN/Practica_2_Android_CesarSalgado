package com.example.practica_2_android_cesarsalgado.ui.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase

@Composable
fun PlayerOptionsActivity(navController: NavController, appDatabase: AppDatabase, userName: String?) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    var mode: String
    Column (Modifier.fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
        Row (Modifier.fillMaxWidth(), Arrangement.Center){
            Text(text = "Welcome back, $userName! Let's answer some questions!", Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
        Button(onClick = {
            mode = "Exam"
            navController.navigate("questionactivity/$mode")
        }) {
            Text(text = "Exam Mode")
        }
        Button(onClick = {
            mode = "Practice"
            navController.navigate("questionactivity/$mode")
        }) {
            Text(text = "Practice Mode")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Ranking")
        }
        Button(onClick = {
            openDialog = true
        }) {
            Text(text = "Delete User")
        }
        if (openDialog) {
            AlertDialog(
                title = {
                    Text(text = "Deleting User")
                },
                text = {
                    Text(text = "Are you sure about deleting the user?")
                },
                onDismissRequest = {
                    openDialog = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        if (userName != null) {
                            appDatabase.getUserDao().getByName(userName)
                        }
                    }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        openDialog = false
                    }) {
                        Text(text = "Dismiss")
                    }
                })
        }
    }
}