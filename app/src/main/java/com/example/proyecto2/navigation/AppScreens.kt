package com.example.proyecto2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object AnimalScreen : AppScreens("animal_screen", "Animals", Icons.Filled.Home)
    object ViewDetailsAnimalScreen :
        AppScreens("view_details_animal_screen", "Animal Details", Icons.Filled.Email)

    object ConfigScreen : AppScreens("config_screen", "Configuration", Icons.Filled.Build)
    object LoginScreen : AppScreens("login_screen", "Login", Icons.Filled.Home)
    object RegisterScreen : AppScreens("register_screen", "Register", Icons.Filled.Create)
}
