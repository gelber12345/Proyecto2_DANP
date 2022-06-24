package com.example.proyecto2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.proyecto2.data.animal.AnimalViewModel
import com.example.proyecto2.data.animal.pagination.PageAnimalVM
import com.example.proyecto2.navigation.AppNavigation
import com.example.proyecto2.navigation.AppScreens
import com.example.proyecto2.screens.components.ButtomNavigationBar
import com.example.proyecto2.ui.theme.Proyecto2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)
        val pageAnimalviewmodel = ViewModelProvider(this).get(PageAnimalVM::class.java)
        setContent {
            Proyecto2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Body(animalViewModel,pageAnimalviewmodel)
                }
            }
        }
    }
}
@Composable
fun Body(animalViewModel: AnimalViewModel, pageAnimalviewmodel: PageAnimalVM) {

    val navController = rememberNavController() // Redirección de vistas

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
            AppNavigation(navController,animalViewModel,pageAnimalviewmodel)
        }
    }

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