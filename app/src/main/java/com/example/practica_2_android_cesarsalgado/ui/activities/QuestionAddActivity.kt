package com.example.practica_2_android_cesarsalgado.ui.activities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.room.Room
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase
import com.example.practica_2_android_cesarsalgado.data.entity.Question
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionAddActivity(navController: NavController, appDatabase: AppDatabase, applicationContext: Context) {
    var selectedImageUri by remember {
        mutableStateOf("")
    }
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        uri -> selectedImageUri = uri.toString()
    }
    var questionString by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    val types = arrayOf("Selection", "True/False")
    var selectedText by remember {
        mutableStateOf(types[0])
    }
    var answers: String
    var image: ByteArray? = null

    Column (Modifier.fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
        Column (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
            Text(text = "Write your question")
            TextField(value = questionString,
                onValueChange = {
                text -> questionString = text
            },
            supportingText = {
                Text(text = "${questionString.length}/150")
            },
            isError = (questionString.length > 150))
        }
        Column (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
            Text(text = "What kind of question is it?")
            Box (contentAlignment = Alignment.Center) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        types.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    selectedText = item
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
        if (selectedText.equals("Selection")) {
            var answer1 by remember {
                mutableStateOf("")
            }
            var answer2 by remember {
                mutableStateOf("")
            }
            var answer3 by remember {
                mutableStateOf("")
            }
            var answer4 by remember {
                mutableStateOf("")
            }
            Column {
                OutlinedTextField(
                    value = answer1,
                    onValueChange = { text -> answer1 = text},
                    label = { Text("Correct Answer") },
                    supportingText = {
                        Text(text = "${answer1.length}/100")
                    },
                    isError = (answer1.length > 100),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Green
                    )
                )
                OutlinedTextField(
                    value = answer2,
                    onValueChange = { text -> answer2 = text},
                    label = { Text("Incorrect Answer") },
                    supportingText = {
                        Text(text = "${answer2.length}/100")
                    },
                    isError = (answer2.length > 100),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Red
                    )
                )
                OutlinedTextField(
                    value = answer3,
                    onValueChange = { text -> answer3 = text},
                    label = { Text("Incorrect Answer") },
                    supportingText = {
                        Text(text = "${answer3.length}/100")
                    },
                    isError = (answer3.length > 100),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Red
                    )
                )
                OutlinedTextField(
                    value = answer4,
                    onValueChange = { text -> answer4 = text},
                    label = { Text("Incorrect Answer") },
                    supportingText = {
                        Text(text = "${answer4.length}/100")
                    },
                    isError = (answer4.length > 100),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Red
                    )
                )
            }
            answers = "$answer1;$answer2;$answer3;$answer4"
        }
        else {
            var answer1 by remember {
                mutableStateOf("")
            }
            var answer2 by remember {
                mutableStateOf("")
            }
            Column {
                OutlinedTextField(
                    value = answer1,
                    onValueChange = { text -> answer1 = text},
                    label = { Text("True Answer") },
                    supportingText = {
                        Text(text = "${answer1.length}/100")
                    },
                    isError = (answer1.length > 100),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Green
                    )
                )
                OutlinedTextField(
                    value = answer2,
                    onValueChange = { text -> answer2 = text},
                    label = { Text("False Answer") },
                    supportingText = {
                        Text(text = "${answer2.length}/100")
                    },
                    isError = (answer2.length > 100),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Red
                    )
                )
            }
            answers = "$answer1;$answer2"
        }
        Row (Modifier.fillMaxWidth(), Arrangement.SpaceAround, Alignment.CenterVertically){
            Button( onClick = {
                getContent.launch("image/*")
                if (selectedImageUri != null) {
                    val imageBitmap: Bitmap = BitmapFactory.decodeFile(selectedImageUri)
                    val stream = ByteArrayOutputStream()
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    image= stream.toByteArray()
                }
            }) {
                Text(text = "Select Image")
            }
            Text(text = selectedImageUri)
        }
        Row (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly){
            Button(onClick = {
                val newQuestion = Toast.makeText(applicationContext,
                    "New question added!", Toast.LENGTH_SHORT)
                val question = Question(0, questionString, selectedText, answers, 0, image)
                appDatabase.getQuestionDao().insert(question)
                newQuestion.show()
                questionString = ""
                println(appDatabase.getQuestionDao().getAll())
            }) {
                Text(text = "Add Question")
            }
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Back")
            }
        }
    }
}