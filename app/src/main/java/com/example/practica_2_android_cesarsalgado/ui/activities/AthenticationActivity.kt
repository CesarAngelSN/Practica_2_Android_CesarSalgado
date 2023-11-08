package com.example.practica_2_android_cesarsalgado.ui.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

//@Preview
@Composable
fun AuthenticationActivity(navController: NavController) {
    Column(Modifier.fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
        Row() {
            //Cambiar en el futuro por texto decorado
            Text(text = "What would you like to do?")
        }
        Column (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
            Button(onClick = {
                navController.navigate("loginactivity")
            }) {
                Text(text = "Log In")
            }
            Button(onClick = {
                navController.navigate("registeractivity")
            }) {
                Text(text = "Register")
            }
        }
    }
}