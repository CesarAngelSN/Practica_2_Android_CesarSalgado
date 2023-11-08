package com.example.practica_2_android_cesarsalgado.ui.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.practica_2_android_cesarsalgado.R
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase
import com.example.practica_2_android_cesarsalgado.data.entity.Question
import java.security.SecureRandom

@Composable
fun QuestionActivity(appDatabase: AppDatabase, mode: String) {
    val questionList = appDatabase.getQuestionDao().getAll().toMutableList()
    println(questionList)
    val questionArrayList = ArrayList(questionList)
    var question by remember {
        mutableStateOf(getRandomQuestion(questionArrayList))
    }
    /*var image by remember {
        mutableStateOf()
    }*/
    val answersString by remember {
        mutableStateOf(question.getAnswers())
    }
    val answersArray = answersString.split(";")
    var color by remember {
        mutableStateOf(Color.Blue)
    }
    Column(Modifier.fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterVertically) {
            //Cambiar en el futuro por texto decorado
            Text(text = question.getDescription())
        }
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.wallace), contentDescription = "Imagen")
        }
        Row (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically){
            if (question.getType() == "True/False") {
                Button(onClick = {
                    color = if (answersArray[0] == question.getCorrectAnswer()) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                }, colors = ButtonDefaults.buttonColors(color)) {
                    Text(text = answersArray[0])
                }
                Button(onClick = {
                    color = if (answersArray[1] == question.getCorrectAnswer()) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                }, colors = ButtonDefaults.buttonColors(color)) {
                    Text(text = answersArray[1])
                }
            }
            else {
                Column {
                    Button(onClick = {
                        color = if (answersArray[0] == question.getCorrectAnswer()) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    }, colors = ButtonDefaults.buttonColors(color)) {
                        Text(text = answersArray[0])
                    }
                    Button(onClick = {
                        color = if (answersArray[1] == question.getCorrectAnswer()) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    }, colors = ButtonDefaults.buttonColors(color)) {
                        Text(text = answersArray[1])
                    }
                }
                Column {
                    Button(onClick = {
                        color = if (answersArray[2] == question.getCorrectAnswer()) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    }, colors = ButtonDefaults.buttonColors(color)) {
                        Text(text = answersArray[2])
                    }
                    Button(onClick = {
                        color = if (answersArray[3] == question.getCorrectAnswer()) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    }, colors = ButtonDefaults.buttonColors(color)) {
                        Text(text = answersArray[3])
                    }
                }
            }
        }
        Row (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically){
            Button(onClick = {

            }) {
                Text(text = "Previous")
            }
            Button(onClick = {
                question = getRandomQuestion(questionArrayList)
            }) {
                Text(text = "Next")
            }
        }
    }
}

fun getRandomQuestion(questionList: ArrayList<Question>): Question{
    val random = SecureRandom().nextInt(questionList.size - 1)
    val question = questionList[random]
    questionList.removeAt(random)
    return question
}