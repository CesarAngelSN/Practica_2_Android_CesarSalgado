package com.example.practica_2_android_cesarsalgado.ui.activities

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.EaseOutElastic
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica_2_android_cesarsalgado.R

//@Preview
@Composable
fun AuthenticationActivity(navController: NavController) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")
    /*val color by infiniteTransition.animateColor(
        initialValue = colorResource(R.color.light_purple),
        targetValue = colorResource(R.color.dark_purple),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "color"
    )*/
    val fontSizeTitle by infiniteTransition.animateFloat(
        initialValue = 4f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseOutCirc),
            repeatMode = RepeatMode.Reverse
        ),
        label = "fontsize"
    )
    Column(
        Modifier
            .background(colorResource(R.color.background))
            .fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo",
            Modifier
                .fillMaxWidth(0.6f)
                .scale(1.2f, 1.2f))
        Column (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
            Button(onClick = {
                navController.navigate("loginactivity")
            },
                Modifier
                    .fillMaxWidth(0.7f)
                    .align(CenterHorizontally)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))) {
                Text(text = "Log In", fontSize = 20.sp, color = colorResource(id = R.color.background))
            }
            Button(onClick = {
                navController.navigate("registeractivity")
            },
                Modifier
                    .fillMaxWidth(0.7f)
                    .align(CenterHorizontally)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))) {
                Text(text = "Register", fontSize = 20.sp, color = colorResource(id = R.color.background))
            }
        }
    }
}