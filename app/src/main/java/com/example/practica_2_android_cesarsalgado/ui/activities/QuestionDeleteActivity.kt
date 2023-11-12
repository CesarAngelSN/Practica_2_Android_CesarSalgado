package com.example.practica_2_android_cesarsalgado.ui.activities

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica_2_android_cesarsalgado.R
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionDeleteActivity(navController: NavController, appDatabase: AppDatabase, applicationContext: Context) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    val questions = appDatabase.getQuestionDao().getAll().toTypedArray()
    var selectedText by remember {
        mutableStateOf(questions[0].getDescription())
    }
    Column(
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
        Text(text = "Select the question you would like to delete.", textAlign = TextAlign.Center, fontSize = 20.sp,
            color = colorResource(id = R.color.dark_green))
        Box(contentAlignment = Alignment.Center) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            )
            {
                TextField(
                    value = "Select a question",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    questions.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item.getDescription()) },
                            onClick = {
                                selectedText = item.getDescription()
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceAround, Alignment.CenterVertically){
            Button(onClick = {
                openDialog = true
            },
                Modifier.width(120.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))) {
                Text(text = "Delete", fontSize = 20.sp, color = colorResource(id = R.color.background))
            }
            Button(onClick = {
                navController.popBackStack()
            },
                Modifier.width(120.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))) {
                Text(text = "Back", fontSize = 20.sp, color = colorResource(id = R.color.background))
            }
        }
        if (openDialog) {
            AlertDialog(
                title = {
                    Text(text = "Deleting Question")
                },
                text = {
                    Text(text = "Are you sure about deleting the question?")
                },
                onDismissRequest = {
                    openDialog = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        appDatabase.getQuestionDao().deleteByDescription(selectedText)
                        val deletedQuestion = Toast.makeText(applicationContext,
                            "Deletion successfully done", Toast.LENGTH_SHORT)
                        deletedQuestion.show()
                        openDialog = false
                        
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
                }
            )
        }
    }
}


