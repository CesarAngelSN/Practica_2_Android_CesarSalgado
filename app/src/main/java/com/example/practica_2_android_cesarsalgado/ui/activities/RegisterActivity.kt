package com.example.practica_2_android_cesarsalgado.ui.activities

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.practica_2_android_cesarsalgado.data.entity.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterActivity(navController: NavController, appDatabase: AppDatabase, applicationContext: Context){
    Column (Modifier.fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally){
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
        Text(text = "Introduce your Information")
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
            OutlinedTextField(
                value = repeatedPassword,
                onValueChange = { text -> repeatedPassword = text },
                label = { Text("Repeat the Password") },
                isError = (repeatedPassword != password),
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
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    users.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
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
            }) {
                Text(text = "Register")
            }
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Back")
            }
        }
    }
}