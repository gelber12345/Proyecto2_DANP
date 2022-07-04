package com.example.proyecto2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyecto2.data.animal.Animal
import com.example.proyecto2.data.animal.AnimalViewModel
import com.example.proyecto2.screens.*

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModelAnimal: AnimalViewModel
    ) {
    //val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(
            route = AppScreens.LoginScreen.route
        ) {
            LoginScreen(navController)
        }

        composable(
            route = AppScreens.RegisterScreen.route
        ) {
            RegisterScreen(navController)
        }

        composable(
            route = AppScreens.AnimalScreen.route
        ) {
            AnimalScreen(navController, viewModelAnimal)
        }

        composable(
            route = AppScreens.ViewDetailsAnimalScreen.route,
        ) {

            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<Animal>("animal")
            if (result != null) {
                ViewAnimalDetailsScreen(navController, result, viewModelAnimal)
            } else {
                ViewAnimalDetailsScreen(
                    navController,
                    Animal(0, "", "", "", "", "", "", "", "", "", "", ""),
                    viewModelAnimal
                )
            }
        }

        composable(
            route = AppScreens.ConfigScreen.route
        ) {
            ConfigScreen(navController)

        }
    }
}