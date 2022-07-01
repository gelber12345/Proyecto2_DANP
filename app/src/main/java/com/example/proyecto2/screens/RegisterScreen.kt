package com.example.proyecto2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto2.navigation.AppScreens

@Composable
fun RegisterScreen(navController: NavHostController) {

    var first_name by remember { mutableStateOf("") }
    var last_name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val onFirstNameChange = { text: String ->
        first_name = text
    }

    val onLastNameChange = { text: String ->
        last_name = text
    }

    val onPhoneChange = { text: String ->
        phone = text
    }

    val onEmailChange = { text: String ->
        email = text
    }
    val onPasswordChange = { text: String ->
        password = text
    }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(18, 114, 163))
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Spacer(Modifier.height(40.0.dp))

            Text(
                text = "Adopt.me",
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Adopción responsable de mascotas",
                fontSize = 16.sp,
                color = Color(208, 227, 237)
            )

            Spacer(Modifier.height(48.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(Color(18, 114, 163)),
                shape = RoundedCornerShape(24.dp)
            )
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Crear una nueva cuenta",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(18.dp)
                    )

                    OutlinedTextField(
                        value = first_name,
                        onValueChange = { first_name = it },
                        label = { Text("Nombres") },
                        placeholder = { Text("Ingrese sus nombres") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "firstNameIcon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = last_name,
                        onValueChange = { last_name = it },
                        label = { Text("Apellidos") },
                        placeholder = { Text("Ingrese sus apellidos") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "lastNameIcon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Teléfono") },
                        placeholder = { Text("Ingrese su teléfono") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "phoneIcon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Correo Electrónico") },
                        placeholder = { Text(text = "Ingrese su correo electrónico") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "emailIcon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        placeholder = { Text(text = "Ingrese su contraseña") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "passwordIcon"
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Spacer(Modifier.height(24.dp))

                    Button(
                        onClick = {
                            navController.navigate(AppScreens.LoginScreen.route)
                        },
                        shape = RoundedCornerShape(20.dp),
                        elevation = ButtonDefaults.elevation(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 24.dp,
                                end = 24.dp
                            ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White,
                            backgroundColor = Color(18, 114, 163)
                        )
                    ) {
                        Text(
                            text = "Registrarse",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Row {
                        Text(
                            text = "¿Ya tienes una cuenta?", Modifier.padding(end = 8.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Iniciar Sesión", fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController.navigate(AppScreens.LoginScreen.route)
                            },
                            color = Color(18, 114, 163)
                        )
                    }

                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}