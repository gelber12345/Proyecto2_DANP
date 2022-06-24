package com.example.proyecto2.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.paging.compose.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.proyecto2.data.Data
import com.example.proyecto2.data.animal.Animal
import com.example.proyecto2.data.animal.AnimalViewModel
import com.example.proyecto2.data.animal.pagination.PageAnimalVM
import com.example.proyecto2.navigation.AppScreens
import kotlinx.coroutines.flow.collect

@Composable
fun AnimalScreen(
    navController: NavController,
    viewModel: AnimalViewModel,
    pageAnimalviewmodel: PageAnimalVM
) {

    val allCentros: LazyPagingItems<Animal> = viewModel.animalFlow.collectAsLazyPagingItems()
    //val allCentros = pageAnimalviewmodel.animalFlow.collectAsLazyPagingItems()
    val searchResults by viewModel.searchResults.observeAsState(listOf())

    FirstMainScreen(
        allCentros = allCentros,
        searchResults = searchResults,
        viewModel = viewModel,
        pager = pageAnimalviewmodel,
        navController = navController
    )
}

@Composable
fun FirstMainScreen(
    allCentros: LazyPagingItems<Animal>,
    searchResults: List<Animal>,
    viewModel: AnimalViewModel,
    pager: PageAnimalVM,
    navController: NavController
) {
    var textSize by rememberSaveable { mutableStateOf("0") }
    var color by rememberSaveable { mutableStateOf("0") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Data(context)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //////////////

        LaunchedEffect(scope){
            dataStore.getColor.collect {
                    data -> color = data.toString()
            }
        }
        LaunchedEffect(scope){
            dataStore.getSize.collect {
                    data -> textSize = data.toString()
            }
        }
        var textBusqueda by remember { mutableStateOf("") }
        var searching by remember { mutableStateOf(false) }

        val onTextChange = { text: String ->
            textBusqueda = text
        }
        CustomTextField(
            title = "BÚSQUEDA ",
            textState = textBusqueda,
            onTextChange = onTextChange,
            keyboardType = KeyboardType.Text
        )
        //////////////
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Button(
                colors= ButtonDefaults.buttonColors(
                    backgroundColor = Color(color.toInt()),
                ),
                onClick = {
                    searching = false
                    navController.navigate(AppScreens.EditAnimalScreen.route)
                    navController.previousBackStackEntry?.savedStateHandle?.remove<Animal>("animal")
                }) {
                Text("AGREGAR",fontSize = textSize.toInt().sp)
            }
            Button(
                onClick = {
                    searching = true
                    viewModel.findAnimal("%$textBusqueda%")
                }) {
                Text("BUSCAR")
            }
            Button(onClick = {
                searching = false
                viewModel.deleteAllAnimal()
            }) {
                Text("ELIMINAR DATOS")
            }
        }
        //////////////
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            val list = if (searching) searchResults else allCentros
            item {
                TitleRow(head1 = "Id", head2 = "Nombre", head3 = "Raza")
            }
            if ( searching){
                items(searchResults) { centro ->
                    CentroRow(centro, navController, viewModel)
                }
            }else{
                items(allCentros) { centro ->
                    centro?.let { CentroRow(it, navController, viewModel) }
                }
            }

        }
    }
}

@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f)
        )
        Text(
            head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f)
        )
        Text(
            head3, color = Color.White,
            modifier = Modifier.weight(0.2f)
        )
    }
}

@Composable
fun CentroRow(animal: Animal, navController: NavController, viewModel: AnimalViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {

                navController.currentBackStackEntry?.savedStateHandle?.set("animal", animal)
                navController.navigate(AppScreens.EditAnimalScreen.route)
            }
    ) {
        Text(
            animal.id.toString(), modifier = Modifier
                .weight(0.1f)
        )
        Text(animal.nombre, modifier = Modifier.weight(0.2f))
        Text(animal.raza, modifier = Modifier.weight(0.2f))
    }
}