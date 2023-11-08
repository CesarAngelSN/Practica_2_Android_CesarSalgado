package com.example.practica_2_android_cesarsalgado.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.practica_2_android_cesarsalgado.data.db.AppDatabase

@SuppressLint("ShowToast")
@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun LoginActivity(navController: NavController, appDatabase: AppDatabase, applicationContext: Context){
    var user by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column (Modifier.fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
        Text(text = "Introduce your Credentials")
        Column (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = user,
                onValueChange = { text -> user = text},
                label = { Text("User") },
                supportingText = {
                    Text(text = "${user.length}/15")
                },
                isError = (user.length > 15 || !user.matches(Regex("^[0-9a-zA-Z-_]*$")))
            )
            OutlinedTextField(
                value = password,
                onValueChange = { text -> password = text },
                label = { Text("Password") },
                supportingText = {
                    Text(text = "${password.length}/15")
                },
                isError = (password.length > 15 || !password.matches(Regex("^[0-9a-zA-Z-_]*$"))),
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Row (Modifier.fillMaxWidth(), Arrangement.SpaceEvenly){
            Button(onClick = {
                val currentUser = appDatabase.getUserDao().getByName(user)
                if (currentUser != null && currentUser.getPassword() == password){
                    val userFound = Toast.makeText(applicationContext,
                        "Login for $user successfully done", Toast.LENGTH_SHORT)
                    userFound.show()
                    if (appDatabase.getUserDao().getByName(user).getType().equals("admin")) {
                        navController.navigate("adminoptionsactivity/$user")
                    }
                    else {
                        navController.navigate("playeroptionsactivity/$user")
                    }
                }
                else if (currentUser != null){
                    val incorrectPassword = Toast.makeText(applicationContext,
                        "Password for user $user is not correct", Toast.LENGTH_SHORT)
                    incorrectPassword.show()
                    password = ""
                }
                else {
                    val userNotExists = Toast.makeText(applicationContext,
                        "User $user does not exist", Toast.LENGTH_SHORT)
                    userNotExists.show()
                    user = ""
                    password = ""
                }
            }) {
                Text(text = "Go")
            }
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Back")
            }
        }
    }
}