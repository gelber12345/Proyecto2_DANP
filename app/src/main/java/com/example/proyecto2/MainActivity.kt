package com.example.proyecto2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.proyecto2.Firebase.FirebaseAPI
import com.example.proyecto2.data.animal.AnimalViewModel
import com.example.proyecto2.navigation.AppNavigation
import com.example.proyecto2.ui.theme.Proyecto2Theme


class MainActivity : ComponentActivity() {
    lateinit var db: FirebaseAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)

        setContent {
            /*Proyecto2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }

            }*/

            Body(animalViewModel)
            db = FirebaseAPI()
            db.updateData(animalViewModel)
        }
    }
}

@Composable
fun Body(animalViewModel: AnimalViewModel) {
    val navController = rememberNavController() // Redirección de vistas
/*
    //BOTTOM NAVIGATION
    val navigationItems = listOf(
        AppScreens.AnimalScreen,
        AppScreens.ConfigScreen
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        bottomBar = { ButtomNavigationBar(navController = navController, items = navigationItems) }
    ) { innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Surface(modifier = Modifier.padding(innerPadding)) {

        }
    }*/
    AppNavigation(navController, animalViewModel)
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Proyecto2Theme {
        Greeting("Android")
    }
}