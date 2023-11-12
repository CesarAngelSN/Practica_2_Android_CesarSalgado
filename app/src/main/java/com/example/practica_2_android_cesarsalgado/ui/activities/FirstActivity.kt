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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.practica_2_android_cesarsalgado.R
import kotlinx.coroutines.delay

//@Preview
@Composable
fun FirstActivity(navController: NavController){
    Box (
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background)), contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo", Modifier.scale(1.3f, 1.3f))
    }
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate("authenticationactivity"){
            popUpTo("firstactivity") {
                inclusive = true
            }
        }
    }
}