package com.example.proyecto2.screens

import android.widget.Toast
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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto2.data.Data
import com.example.proyecto2.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var textSize by rememberSaveable { mutableStateOf("0") }
    var color by rememberSaveable { mutableStateOf("0") }
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Data(context)

    LaunchedEffect(scope) {
        dataStore.getEmail.collect { data ->
            email = data
        }
    }
    LaunchedEffect(scope) {
        dataStore.getPassword.collect { data ->
            password = data
        }
    }
    LaunchedEffect(scope) {
        dataStore.getSize.collect { data ->
            textSize = data.toString()
        }
    }
    LaunchedEffect(scope) {
        dataStore.getColor.collect { data ->
            color = data.toString()
        }
    }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(color.toInt()))
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
                    .background(Color(color.toInt())),
                shape = RoundedCornerShape(24.dp)
            )
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Bienvenido",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(18.dp)
                    )

                    OutlinedTextField(
                        value = email,

                        onValueChange = { email = it },
                        label = { Text("Correo Electrónico") },
                        placeholder = {
                            Text(
                                text = "Ingrese su correo electrónico",
                                fontSize = textSize.toInt().sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "emailIcon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        placeholder = {
                            Text(
                                text = "Ingrese su contraseña",
                                fontSize = textSize.toInt().sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "passwordIcon"
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true
                    )

                    Spacer(Modifier.height(24.dp))

                    Button(
                        onClick = {
                            db.collection("users")
                                .whereEqualTo("email", email)
                                .whereEqualTo("password", password).get()
                                .addOnSuccessListener { users ->
                                    if (users.size() != 0) {
                                        Toast.makeText(
                                            context,
                                            "Bienvenido",
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                        scope.launch {
                                            dataStore.saveEmail(email)

                                        }
                                        scope.launch{
                                            dataStore.savePassword(password)
                                        }
                                        navController.navigate(AppScreens.AnimalScreen.route)
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "El usuario no se encuentra registrado.",
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    Toast.makeText(
                                        context,
                                        "Error - ${exception}",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                }
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
                            backgroundColor = Color(color.toInt())
                        )
                    ) {
                        Text(
                            text = "Iniciar Sesión",
                            fontWeight = FontWeight.Bold,
                            fontSize = textSize.toInt().sp
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Row {
                        Text(
                            text = "¿Nuevo en Adopt.me?", Modifier.padding(end = 8.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Crear una cuenta", fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController.navigate(AppScreens.RegisterScreen.route)
                            },
                            color = Color(color.toInt())
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "Configuraciones",
                        fontWeight = FontWeight.Bold,
                        fontSize = textSize.toInt().sp,
                        modifier = Modifier.clickable {
                            navController.navigate(AppScreens.ConfigScreen.route)
                        },
                        color = Color(color.toInt())
                    )
                    Spacer(Modifier.height(16.dp))

                }
            }
        }
    }
}

