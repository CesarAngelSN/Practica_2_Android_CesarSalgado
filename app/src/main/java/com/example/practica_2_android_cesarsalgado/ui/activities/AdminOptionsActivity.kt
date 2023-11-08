package com.example.practica_2_android_cesarsalgado.ui.activities

import android.content.Context
import android.widget.Toast
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
fun AdminOptionsActivity(navController: NavController, appDatabase: AppDatabase, applicationContext: Context, userName: String?) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    Column(Modifier.fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            Text(text = "Hello again $userName. What would you like to do?", textAlign = TextAlign.Center)
        }
        Button(onClick = {
            navController.navigate("questionaddactivity")
        }) {
            Text(text = "Add Question")
        }
        Button(onClick = {
            navController.navigate("questiondeleteactivity")
        }) {
            Text(text = "Delete Question")
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
                        appDatabase.getUserDao().deleteByName(userName)
                        val deletedUser = Toast.makeText(applicationContext,
                            "Deletion for $userName successfully done", Toast.LENGTH_SHORT)
                        deletedUser.show()
                        navController.popBackStack()
                        openDialog = false
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
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Back")
        }
    }
}