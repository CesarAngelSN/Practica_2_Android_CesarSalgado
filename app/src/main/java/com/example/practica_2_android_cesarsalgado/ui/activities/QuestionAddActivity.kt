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
    var answers: String = String()
    var image: ByteArray? = null
    var isErrorTitle by remember {
        mutableStateOf(true)
    }
    Column (Modifier.fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
        Column (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
            Text(text = "Write your question")
            TextField(value = questionString,
                onValueChange = {
                text -> questionString = text
                    isErrorTitle = questionString.length > 150 || questionString.isEmpty()
            },
            supportingText = {
                Text(text = "${questionString.length}/150")
            },
            isError = isErrorTitle
        )}
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
        var isError1 by remember {
            mutableStateOf(true)
        }
        var isError2 by remember {
            mutableStateOf(true)
        }
        var isError3 by remember {
            mutableStateOf(true)
        }
        var isError4 by remember {
            mutableStateOf(true)
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
                    onValueChange = { text -> answer1 = text
                        isError1 = answer1.length > 100},
                    label = { Text("Correct Answer") },
                    supportingText = {
                        Text(text = "${answer1.length}/100")
                    },
                    isError = isError1,
                )
                OutlinedTextField(
                    value = answer2,
                    onValueChange = { text -> answer2 = text
                        isError2 = answer2.length > 100},
                    label = { Text("Incorrect Answer") },
                    supportingText = {
                        Text(text = "${answer2.length}/100")
                    },
                    isError = isError2,
                )
                OutlinedTextField(
                    value = answer3,
                    onValueChange = { text -> answer3 = text
                        isError3 = answer3.length > 100},
                    label = { Text("Incorrect Answer") },
                    supportingText = {
                        Text(text = "${answer3.length}/100")
                    },
                    isError = isError3,
                )
                OutlinedTextField(
                    value = answer4,
                    onValueChange = { text -> answer4 = text
                        isError4 = answer4.length > 100},
                    label = { Text("Incorrect Answer") },
                    supportingText = {
                        Text(text = "${answer4.length}/100")
                    },
                    isError = isError4,
                )
            }
            answers = (if(answer1.isNotEmpty()) "$answer1;" else "" + if(answer2.isNotEmpty()) "$answer2;" else "" +
                    if(answer3.isNotEmpty()) "$answer3;" else "" + if(answer4.isNotEmpty()) "$answer4;" else "")
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
                    onValueChange = { text -> answer1 = text
                        isError1 = answer1.length > 100 || answer1.isEmpty()},
                    label = { Text("True Answer") },
                    supportingText = {
                        Text(text = "${answer1.length}/100")
                    },
                    isError = isError1,
                )
                OutlinedTextField(
                    value = answer2,
                    onValueChange = { text -> answer2 = text
                        isError2 = answer2.length > 100 || answer2.isEmpty()},
                    label = { Text("False Answer") },
                    supportingText = {
                        Text(text = "${answer2.length}/100")
                    },
                    isError = isError2,
                )
            }
            answers = (if(answer1.isNotEmpty()) "$answer1;" else "" + if(answer2.isNotEmpty()) "$answer2;" else "")
        }
        Row (Modifier.fillMaxWidth(), Arrangement.SpaceAround, Alignment.CenterVertically){
            var selectedImageUri by remember {
                mutableStateOf("")
            }
            val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
                    uri -> selectedImageUri = uri.toString()
            }
            Button( onClick = {
                getContent.launch("image/*")
                if (selectedImageUri.isNotEmpty()) {
                    if (selectedImageUri ==  null) {
                        selectedImageUri = ""
                        val noURIFound = Toast.makeText(applicationContext,
                            "No URI found!", Toast.LENGTH_SHORT)
                        noURIFound.show()
                    }
                    else {
                        val imageBitmap: Bitmap = BitmapFactory.decodeFile(selectedImageUri)
                        val stream = ByteArrayOutputStream()
                        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                        image= stream.toByteArray()
                    }
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
            },
            enabled = !isErrorTitle && !isError1 && !isError2 && !isError3 && !isError4) {
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