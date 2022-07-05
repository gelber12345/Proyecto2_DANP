package com.example.proyecto2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.proyecto2.Firebase.FirebaseAPI
import com.example.proyecto2.data.Data
import com.example.proyecto2.data.animal.AnimalViewModel
import com.example.proyecto2.navigation.AppNavigation
import com.example.proyecto2.ui.theme.Proyecto2Theme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {
    lateinit var db: FirebaseAPI
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
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

            var database by rememberSaveable{ mutableStateOf("-1") }

            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            val dataStore = Data(context)



            LaunchedEffect(scope) {
                dataStore.getDatabase.collect { data ->
                    database = data.toString()
                }
            }

            if (database == "0"){
                db = FirebaseAPI()
                db.updateData(animalViewModel)
                LaunchedEffect(scope) {
                    dataStore.saveDatabase(1)
                }

            }
            Body(animalViewModel)
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