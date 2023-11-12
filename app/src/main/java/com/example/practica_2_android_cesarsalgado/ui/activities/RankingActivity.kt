package com.example.practica_2_android_cesarsalgado.ui.activities

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.navigation.NavController
import com.example.practica_2_android_cesarsalgado.R
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase

@Composable
fun RankingActivity(appDatabase: AppDatabase, navController: NavController) {
    val usersArrayList = ArrayList(appDatabase.getUserDao().getAll())
    usersArrayList.sort()
    Column(
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
        Text(text = "Current User Ranking", color  = colorResource(id = R.color.dark_green), fontWeight = FontWeight.Bold,
            fontSize = 20.sp, textAlign = TextAlign.Center)
        Box(
            Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.6f), contentAlignment = Alignment.TopCenter) {
            LazyColumn {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray), Arrangement.Center,
                        Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Player",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Correct Answered Questions",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Games Played",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                itemsIndexed(usersArrayList) { index, user ->
                    if (user.getType() == "player") {
                        Row(
                            modifier = Modifier
                                .background(color = colorResource(id = R.color.light_green))
                                .fillMaxWidth()
                                .fillMaxHeight(0.1f),
                                Arrangement.Center
                        ) {
                            Text(
                                text = user.getName(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = user.getTotalCorrectAnswers().toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = user.getGamesPlayed().toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
        Button(onClick = {
            navController.popBackStack()
        },
            Modifier.fillMaxWidth(0.6f),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_green))) {
            Text(text = "Back", fontSize = 18.sp, color = colorResource(id = R.color.background))
        }
    }
}