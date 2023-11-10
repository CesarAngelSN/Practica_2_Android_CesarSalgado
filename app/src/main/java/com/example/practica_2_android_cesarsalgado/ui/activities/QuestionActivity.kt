package com.example.practica_2_android_cesarsalgado.ui.activities

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.practica_2_android_cesarsalgado.R
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase
import com.example.practica_2_android_cesarsalgado.data.entity.Question
import java.security.SecureRandom
import java.util.Random

@Composable
fun QuestionActivity(appDatabase: AppDatabase, applicationContext: Context, mode: String?) {
    val questionList = appDatabase.getQuestionDao().getAll().toMutableList()
    println(questionList)
    val questionArrayList = ArrayList(questionList)
    var i by remember {
        mutableIntStateOf(0)
    }
    var question by remember {
        mutableStateOf(questionArrayList[i])
    }
    /*var image by remember {
        mutableStateOf()
    }*/
    val answersString = question.getAnswers()
    val answersArray = answersString.split(";").shuffled(Random(1))
    val progressFraction = 1.0f / questionArrayList.size
    var progress by remember {
        mutableFloatStateOf(0.0f)
    }
    var correctAnswers by remember {
        mutableIntStateOf(0)
    }
    var color by remember {
        mutableStateOf(Color.Blue)
    }

    Column(Modifier.fillMaxSize(), Arrangement.SpaceAround, Alignment.CenterHorizontally) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
            Text(text = "Progress: " + (progress * 100).toInt() + "%", textAlign = TextAlign.Center)
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.1f),
                contentColorFor(Color.White)
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f), Arrangement.Center, Alignment.CenterVertically) {
            //Cambiar en el futuro por texto decorado
            Text(text = question.getDescription(), fontWeight = FontWeight.Bold)
        }
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.wallace), contentDescription = "Imagen")
        }
        Column (
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
            answersArray.forEach { answer ->
                val isCorrectAnswer = answer == question.getCorrectAnswer()
                var isSelected by remember {
                    mutableStateOf(false)
                }

                Button(
                    onClick = {
                        isSelected = !isSelected
                        color = if (isCorrectAnswer) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    },
                    colors = ButtonDefaults.buttonColors(if (isSelected) color else Color.Blue)
                ) {
                    Text(text = answer)
                }
                if (isCorrectAnswer) {
                    //appDatabase.getUserDao().getByName(userName).setTotalCorrectAnswers()
                    correctAnswers++
                }
            }
        }
        Row (
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f), Arrangement.SpaceEvenly, Alignment.CenterVertically){
            if (mode == "Practice") {
                Button(onClick = {
                    if (i > 0) {
                        i--
                        question = questionArrayList[i]
                        progress -= progressFraction
                        color = Color.Blue
                    }
                    else {
                        val previousQuestion = Toast.makeText(applicationContext,
                            "There is no previous question yet.", Toast.LENGTH_SHORT)
                        previousQuestion.show()
                    }
                }) {
                    Row(Modifier.fillMaxWidth(0.25f), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                        Text(text = "Prev")
                        Icon(imageVector= Icons.Filled.ArrowBack, contentDescription = "Arrow back")
                    }
                }
                Button(onClick = {
                    question = getRandomQuestion(questionArrayList)
                }) {
                    Row(Modifier.fillMaxWidth(0.15f), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                        Text(text = "Random")
                    }
                }
            }
            Button(onClick = {
                if (i < questionArrayList.size - 1) {
                    i++
                    question = questionArrayList[i]
                    progress += progressFraction
                    color = Color.Blue
                }
                else if (i == questionArrayList.size) {

                }
                else {
                    val lastQuestion = Toast.makeText(applicationContext,
                        "Arrived to the last question.", Toast.LENGTH_SHORT)
                    lastQuestion.show()
                }
            }) {
                Row(Modifier.fillMaxWidth(0.25f), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                    Text(text = "Next")
                    Icon(imageVector= Icons.Filled.ArrowForward, contentDescription = "Arrow forward")
                }
            }
        }
    }
}

fun getRandomQuestion(questionList: ArrayList<Question>): Question {
    val random = SecureRandom().nextInt(questionList.size)
    return questionList[random]
}