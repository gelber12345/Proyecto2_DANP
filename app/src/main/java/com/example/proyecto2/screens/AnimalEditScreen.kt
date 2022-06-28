package com.example.proyecto2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto2.data.animal.Animal
import com.example.proyecto2.data.animal.AnimalViewModel
import com.example.proyecto2.navigation.AppScreens
import com.example.proyecto2.screens.components.CustomTextField


@Composable
fun AnimalEditScreen(navController: NavController, animal: Animal, viewModel: AnimalViewModel) {

    var nombre by remember { mutableStateOf(animal.nombre.toString()) }
    var raza by remember { mutableStateOf(animal.raza.toString()) }
    var edad by remember { mutableStateOf(animal.edad.toString()) }

    val onNombreTextChange = { text: String ->
        nombre = text
    }

    val onRazaTextChange = { text: String ->
        raza = text
    }
    val onEdadTextChange = { text: String ->
        edad = text
    }




    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(scrollState)

    ) {
        CustomTextField(
            title = "Nombre",
            textState = nombre,
            onTextChange = onNombreTextChange,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            title = "Raza ",
            textState = raza,
            onTextChange = onRazaTextChange,
            keyboardType = KeyboardType.Number
        )

        CustomTextField(
            title = "Edad",
            textState = edad,
            onTextChange = onEdadTextChange,
            keyboardType = KeyboardType.Number
        )

        if (animal.id != 0) {
            Button(onClick = {
                viewModel.deleteAnimalById(animal.id)
                navController.navigate(AppScreens.AnimalScreen.route)
            }) {
                Text("ELIMINAR")
            }
            Spacer(Modifier.height(20.0.dp))
            Button(onClick = {
                viewModel.updateAnimal(
                    Animal(
                        animal.id,
                        nombre,
                        raza,
                        edad.toInt()
                    )
                )
                navController.navigate(AppScreens.AnimalScreen.route)
            }) {
                Text("GUARDAR")
            }
        } else {
            Button(onClick = {
                viewModel.insertAnimal(
                    Animal(
                        animal.id,
                        nombre,
                        raza,
                        edad.toInt()
                    )
                )
                navController.navigate(AppScreens.AnimalScreen.route)
            }) {
                Text("AGREGAR")
            }
        }
    }
}
