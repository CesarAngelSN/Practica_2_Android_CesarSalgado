package com.example.practica_2_android_cesarsalgado.ui.activities

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica_2_android_cesarsalgado.R
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase
import com.example.practica_2_android_cesarsalgado.data.entity.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterActivity(navController: NavController, appDatabase: AppDatabase, applicationContext: Context){
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")
    val bounce by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "bounce"
    )
    Column (
        Modifier
            .background(colorResource(R.color.background))
            .fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
        var user by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var repeatedPassword by remember {
            mutableStateOf("")
        }
        val users = arrayOf("Admin", "Player")
        var expanded by remember {
            mutableStateOf(false)
        }
        var selectedText by remember {
            mutableStateOf(users[0])
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
        Image(painter = painterResource(id = R.drawable.brain), contentDescription = "brain", Modifier.scale(1.3f, 1.3f).
        graphicsLayer(translationY = bounce))
        Text(text = "Introduce your Information", fontSize = 20.sp, color = colorResource(R.color.dark_green))
        Column (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = user,
                onValueChange = { text -> user = text
                    isError1 = user.length > 15 || !user.matches(Regex("^[0-9a-zA-Z-_]*$")) || user.isEmpty() },
                label = { Text("User", color = colorResource(R.color.dark_green)) },
                supportingText = {
                    Text(text = "${user.length}/15")
                },
                isError = isError1
            )
            OutlinedTextField(
                value = password,
                onValueChange = { text -> password = text
                    isError2 = password.length > 15 || !password.matches(Regex("^[0-9a-zA-Z-_]*$")) || password.isEmpty()},
                label = { Text("Password", color = colorResource(R.color.dark_green)) },
                supportingText = {
                    Text(text = "${password.length}/15")
                },
                isError = isError2,
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextField(
                value = repeatedPassword,
                onValueChange = { text -> repeatedPassword = text
                    isError3 = repeatedPassword != password},
                label = { Text("Repeat the Password", color = colorResource(R.color.dark_green)) },
                isError = isError3,
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Box (Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
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
                    modifier = Modifier.menuAnchor(),
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    users.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item, color = colorResource(R.color.background)) },
                            onClick = {
                                selectedText = item
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
        Row (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly){
            Button(onClick = {
                val newUser = Toast.makeText(applicationContext,
                    "Register for $user successfully done", Toast.LENGTH_SHORT)
                val wrongUser = Toast.makeText(applicationContext,
                    "User $user already exists", Toast.LENGTH_SHORT)
                if (selectedText.equals("Admin")) {
                    if (appDatabase.getUserDao().getByName(user) == null) {
                        var newAdmin = User(0, "admin", user, password, 0, 0)
                        appDatabase.getUserDao().insert(newAdmin)
                        newUser.show()
                        navController.navigate("adminoptionsactivity/$user") {
                            popUpTo("registeractivity") {
                                inclusive = true
                            }
                        }
                    }
                    else {
                        wrongUser.show()
                    }

                }
                else {
                    if (appDatabase.getUserDao().getByName(user) == null) {
                        var newPlayer = User(0, "player", user, password, 0, 0)
                        appDatabase.getUserDao().insert(newPlayer)
                        println(appDatabase.getUserDao().getAll())
                        newUser.show()
                        navController.navigate("playeroptionsactivity/$user") {
                            popUpTo("registeractivity") {
                                inclusive = true
                            }
                        }
                    }
                    else {
                        wrongUser.show()
                        user = ""
                        password = ""
                        repeatedPassword = ""
                    }
                }
            },
                Modifier.width(140.dp),
                enabled = !isError1 && !isError2 && !isError3,
                colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green)),) {
                Text(text = "Register", fontSize = 18.sp, color = colorResource(id = R.color.background))
            }
            Button(onClick = {
                navController.popBackStack()
            }, Modifier.width(140.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))) {
                Text(text = "Back", fontSize = 18.sp, color = colorResource(id = R.color.background))
            }
        }
    }
}