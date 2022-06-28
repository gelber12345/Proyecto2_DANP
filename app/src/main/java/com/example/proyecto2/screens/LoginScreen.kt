package com.example.proyecto2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto2.navigation.AppScreens
import com.example.proyecto2.screens.components.CustomTextField

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val onEmailChange = { text: String ->
        email = text
    }
    val onPasswordChange = { text: String ->
        password = text
    }
    Box(Modifier.fillMaxSize(),
           contentAlignment = Alignment.Center,
           ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color(247, 149, 128))
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

           ) {
            Spacer(Modifier.height(40.0.dp))
            Text(text = "Responsible pet adoption", fontSize = 20.sp, color = Color.White )
            Spacer(Modifier.height(30.0.dp))
            Text(text = "ADOPT.ME" , fontSize =70.sp , color = Color.White)
            Spacer(Modifier.height(100.0.dp))

            Card(
                modifier = Modifier.fillMaxWidth()

                    .background(Color(247, 149, 128)),
                elevation = 20.dp,
                shape= RoundedCornerShape(20.dp),
                contentColor = Color(247, 149, 128))
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally,) {


                    CustomTextField(
                        title = "E-MAIL ",
                        textState = email,
                        onTextChange = onEmailChange,
                        keyboardType = KeyboardType.Text
                    )
                    Spacer(Modifier.height(20.0.dp))
                    CustomTextField(
                        title = "Contraseña ",
                        textState = password,
                        onTextChange = onPasswordChange,
                        keyboardType = KeyboardType.Password
                    )

                    Spacer(Modifier.height(20.0.dp))
                    Button(onClick = { navController.navigate(AppScreens.AnimalScreen.route) }) {
                        Text(text = "INICIAR SESION")
                    }
                    Spacer(Modifier.height(80.0.dp))
                }
            }
        }
    }

}

