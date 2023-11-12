package com.example.practica_2_android_cesarsalgado.ui.activities

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica_2_android_cesarsalgado.R
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase
import com.example.practica_2_android_cesarsalgado.data.entity.Question
import java.security.SecureRandom
import java.util.Random


var isAnswered = false
var correctAnswers = 0
@Composable
fun QuestionActivity(appDatabase: AppDatabase, applicationContext: Context, mode: String?, userName: String?) {
    val questionList = appDatabase.getQuestionDao().getAll().toMutableList()
    println(questionList)
    val questionArrayList = ArrayList(questionList)
    var i by remember {
        mutableIntStateOf(0)
    }
    var question by remember {
        mutableStateOf(questionArrayList[0])
    }
    val images = arrayListOf(R.drawable.blackquestionmark, R.drawable.bluequestionmark, R.drawable.brownquestionmark,
        R.drawable.greenquestionmark, R.drawable.yellowquestionmark, R.drawable.orangequestionmark, R.drawable.redquestionmark,
        R.drawable.purplequestionmark, R.drawable.greyquestionmark)
    var imageId by remember {
        mutableIntStateOf(images[0])
    }
    val answersString = question.getAnswers()
    val answersArray = answersString.split(";").shuffled()
    val progressFraction = 1.0f / questionArrayList.size
    var progress by remember {
        mutableFloatStateOf(0.0f)
    }
    var color = R.color.dark_green
    /*var color by remember {
        mutableIntStateOf(R.color.dark_green)
    }*/
    /*var selectedAnswerIndex by remember {
        mutableIntStateOf(-1)
    }
    var isCorrectAnswer by remember {
        mutableStateOf(false)
    }*/

    Column(
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize(), 
        Arrangement.SpaceAround, Alignment.CenterHorizontally) {
        if (mode == "Exam") {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
                Text(text = "Progress: " + (progress * 100).toInt() + "%", color = colorResource(id = R.color.dark_green),
                    fontSize = 18.sp, textAlign = TextAlign.Center)
                LinearProgressIndicator(
                    progress = progress,
                    color = colorResource(id = R.color.light_green),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.1f).padding(10.dp))
            }
        }
        Row(
            Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.15f), Arrangement.Center, Alignment.CenterVertically) {
            //Cambiar en el futuro por texto decorado
            Text(text = question.getDescription(), fontWeight = FontWeight.Bold, color = colorResource(
                id = R.color.dark_green), textAlign = TextAlign.Center)
        }
        Box(
            Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.3f), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(imageId), contentDescription = "Imagen")
        }
        Column (
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
            if (question.getType() == "Selection") {
                ButtonAnswers(correctAnswer = question.getCorrectAnswer(), currentAnswer = answersArray[0], color, appDatabase, userName)
                ButtonAnswers(correctAnswer = question.getCorrectAnswer(), currentAnswer = answersArray[1], color, appDatabase, userName)
                ButtonAnswers(correctAnswer = question.getCorrectAnswer(), currentAnswer = answersArray[2], color, appDatabase, userName)
                ButtonAnswers(correctAnswer = question.getCorrectAnswer(), currentAnswer = answersArray[3], color, appDatabase, userName)
            }
            else {
                ButtonAnswers(correctAnswer = question.getCorrectAnswer(), currentAnswer = answersArray[0], color, appDatabase, userName)
                ButtonAnswers(correctAnswer = question.getCorrectAnswer(), currentAnswer = answersArray[1], color, appDatabase, userName)
            }
            /*answersArray.forEachIndexed { index, answer ->
                isCorrectAnswer = answer == question.getCorrectAnswer()
                Button(
                    onClick = {
                        if (selectedAnswerIndex == -1) {
                            selectedAnswerIndex = index
                            color = if (isCorrectAnswer) {
                                R.color.light_green
                            } else {
                                R.color.red
                            }
                        }
                    }, Modifier.fillMaxWidth(0.6f),
                    colors = ButtonDefaults.buttonColors(colorResource(id = color))
                ) {
                    Text(text = answer, color = colorResource(id = R.color.background))
                }
                if (isCorrectAnswer) {
                    appDatabase.getUserDao().updateCorrectQuestionsAnswered(userName, 1)
                }
            }*/
        }
        Row (
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f), Arrangement.SpaceEvenly, Alignment.CenterVertically){
            if (mode == "Practice") {
                Button(onClick = {
                    if (i == 0) {
                        i = questionArrayList.size - 1
                        question = questionArrayList[i]
                    }
                    else {
                        question = questionArrayList[--i]
                    }
                    imageId = getRandomImage(images)
                    color = R.color.dark_green
                    isAnswered = false
                    //isCorrectAnswer = false
                    //selectedAnswerIndex =-1
                }) {
                    Row(Modifier.width(60.dp), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                        Icon(imageVector= Icons.Filled.ArrowBack, contentDescription = "Arrow back")
                        Text(text = "Prev")
                    }
                }
                Button(onClick = {
                    question = getRandomQuestion(questionArrayList)
                    imageId = getRandomImage(images)
                    color = R.color.dark_green
                    isAnswered = false
                    //isCorrectAnswer = false
                    //selectedAnswerIndex =-1
                }) {
                    Row(Modifier.width(60.dp), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                        Text(text = "Random")
                    }
                }
            }
            Button(onClick = {
                if (mode == "Practice" && i == questionArrayList.size - 1) {
                    i = 0
                    question = questionArrayList[i]
                }
                else if (i < questionArrayList.size - 1) {
                    question = questionArrayList[++i]
                    progress += progressFraction
                    //color = R.color.dark_green
                }
                else if (mode == "Exam"){
                    /*val lastQuestion = Toast.makeText(applicationContext,
                        "Arrived to the last question.", Toast.LENGTH_SHORT)
                    lastQuestion.show()*/
                }
                imageId = getRandomImage(images)
                color = R.color.dark_green
                isAnswered = false
                //isCorrectAnswer = false
                //selectedAnswerIndex =-1
            }) {
                Row(Modifier.width(60.dp), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
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

fun getRandomImage(images: ArrayList<Int>): Int {
    val random = SecureRandom().nextInt(images.size)
    return images[random]
}

//DESCUBRIR CÓMO REINICIAR EL VALOR DE COLOR PARA QUE NO SE PASE A OTRAS PREGUNTAS
@Composable
fun ButtonAnswers(correctAnswer: String, currentAnswer: String, defaultColor: Int, appDatabase: AppDatabase, user: String?) {
    var color by remember {
        mutableIntStateOf(defaultColor)
    }
    Button(onClick = {
        if (!isAnswered) {
            if (correctAnswer == currentAnswer) {
                color = R.color.light_green
                correctAnswers++
                appDatabase.getUserDao().updateCorrectQuestionsAnswered(user)
            }
            else {
                color = R.color.red
            }
            isAnswered = true
        }
    },
        Modifier.fillMaxWidth(0.6f),
        colors = ButtonDefaults.buttonColors(colorResource(id = color))) {
        Text(text = currentAnswer, color = colorResource(id = R.color.background))
    }
}