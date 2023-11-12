package com.example.practica_2_android_cesarsalgado.ui.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica_2_android_cesarsalgado.R
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase

@Composable
fun StatsActivity(navController: NavController, appDatabase: AppDatabase, correctAnswers: String?) {
    val questionListSize = appDatabase.getQuestionDao().getAll().size
    var message: String = String()
    Column(Modifier.background(colorResource(id = R.color.background)).
    fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
        Text(text = "Exam Results", fontSize = 25.sp, color = colorResource(id = R.color.dark_green))
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
            Text(text = "Your Score", fontSize = 20.sp, color = colorResource(id = R.color.dark_green))
            if (correctAnswers != null) {
                Text(text = "$correctAnswers/$questionListSize", fontSize = 35.sp, color = colorResource(id = R.color.light_green))
            }
            if (correctAnswers?.toInt() == questionListSize) {
                message = "Congrats! You did perfect!"
            }
            else if (correctAnswers?.toInt()!! < questionListSize/2) {
                message = "Haber estudiao."
            }
            else {
                message = "Not bad! If you try again, you will do it perfect."
            }
        }
        Text(text = message, Modifier.fillMaxWidth(0.8f), fontSize = 20.sp,
            color = colorResource(id = R.color.dark_green), textAlign = TextAlign.Center)
        Button(onClick = {
            navController.popBackStack()
        },
            Modifier.fillMaxWidth(0.6f),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))) {
            Text(text = "Back", fontSize = 18.sp, color = colorResource(id = R.color.background))
        }
    }
}