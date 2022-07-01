package com.example.proyecto2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object AnimalScreen : AppScreens("animal_screen", "Centros", Icons.Filled.Home)
    object EditAnimalScreen : AppScreens("editAnimalScreen", "Edit Centro", Icons.Filled.Email)
    object ConfigScreen : AppScreens("config_screen", "Configuracion", Icons.Filled.Build)
    object LoginScreen : AppScreens("login_screen", "Login", Icons.Filled.Home)
    object RegisterScreen : AppScreens("register_screen", "Register", Icons.Filled.Create)
}
