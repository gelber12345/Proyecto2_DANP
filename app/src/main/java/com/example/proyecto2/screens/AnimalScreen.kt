package com.example.proyecto2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.proyecto2.R
import com.example.proyecto2.data.Data
import com.example.proyecto2.data.animal.Animal
import com.example.proyecto2.data.animal.AnimalViewModel
import com.example.proyecto2.data.animal.pagination.PageAnimalVM
import com.example.proyecto2.navigation.AppScreens

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
        navController = navController
    )
}

@Composable
fun FirstMainScreen(
    allCentros: LazyPagingItems<Animal>,
    searchResults: List<Animal>,
    viewModel: AnimalViewModel,
    navController: NavController
) {
    var textSize by rememberSaveable { mutableStateOf("0") }
    var color by rememberSaveable { mutableStateOf("0") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Data(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LaunchedEffect(scope) {
            dataStore.getColor.collect { data ->
                color = data.toString()
            }
        }
        LaunchedEffect(scope) {
            dataStore.getSize.collect { data ->
                textSize = data.toString()
            }
        }

        var textSearch by remember { mutableStateOf("") }
        var searching by remember { mutableStateOf(false) }
        var filter by remember { mutableStateOf("") }
        val onTextChange = { text: String ->
            textSearch = text
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Buscar una mascota",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = textSearch,
            onValueChange = { textSearch = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = { Text("Buscar") },
            shape = RoundedCornerShape(32.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "searchIcon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

        //////////////
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Button(
                colors = buttonColors(
                    backgroundColor = Color(246, 246, 246),
                ),
                onClick = {
                    searching = true
                    filter = "gato"
                    viewModel.findAnimal("%$filter%")
                    //navController.navigate(AppScreens.EditAnimalScreen.route)
                    //navController.previousBackStackEntry?.savedStateHandle?.remove<Animal>("animal")
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(id = R.drawable.cat),
                        contentDescription = "Gatos",
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp),
                        tint = Color.Unspecified
                    )
                    Text("Gatos", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            Button(
                colors = buttonColors(
                    backgroundColor = Color(246, 246, 246),
                ),
                onClick = {
                    searching = true
                    filter = "perro"
                    viewModel.findAnimal("%$filter%")
                    //navController.navigate(AppScreens.EditAnimalScreen.route)
                    //navController.previousBackStackEntry?.savedStateHandle?.remove<Animal>("animal")
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(id = R.drawable.dog),
                        contentDescription = "Perros",
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp),
                        tint = Color.Unspecified
                    )
                    Text("Perros", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            Button(
                colors = buttonColors(
                    backgroundColor = Color(246, 246, 246),
                ),
                onClick = {
                    searching = false
//                    filter = "ave"
//                    viewModel.findAnimal("%$filter%")
                    navController.navigate(AppScreens.EditAnimalScreen.route)
                    navController.previousBackStackEntry?.savedStateHandle?.remove<Animal>("animal")
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(id = R.drawable.bird),
                        contentDescription = "Aves",
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp),
                        tint = Color.Unspecified
                    )
                    Text("Aves", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            Button(
                colors = buttonColors(
                    backgroundColor = Color(246, 246, 246),
                ),
                onClick = {
                    searching = false
                    //filter = "otros"
                    //viewModel.findAnimal("%$filter%")
                    navController.navigate(AppScreens.EditAnimalScreen.route)
                    navController.previousBackStackEntry?.savedStateHandle?.remove<Animal>("animal")
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(id = R.drawable.other),
                        contentDescription = "Otros",
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp),
                        tint = Color.Unspecified
                    )
                    Text("Otros", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Button(
            onClick = {
                searching = true
                viewModel.findAnimal("%$textSearch%")
            }) {

            Text("BUSCAR")
        }
        Button(onClick = {
            searching = false

        }) {
            Text("TODO")
        }

        //////////////
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {


            if (searching) {
                items(searchResults) { centro ->
                    AnimalRow(centro, navController, viewModel)
                    Spacer(Modifier.height(10.0.dp))
                }
            } else {
                items(allCentros) { centro ->
                    centro?.let {
                        AnimalRow(it, navController, viewModel)
                        Spacer(Modifier.height(10.0.dp))
                    }
                }
            }

        }
    }
}

@Composable
fun AnimalRow(animal: Animal, navController: NavController, viewModel: AnimalViewModel) {
    Card(
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(240, 240, 240),
        modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clickable {
                    navController.currentBackStackEntry?.savedStateHandle?.set("animal", animal)
                    navController.navigate(AppScreens.EditAnimalScreen.route)
                }
        ) {

            val painter = rememberImagePainter(
                data = "https://picsum.photos/id/237/200/300",
                builder = {
                    placeholder(R.drawable.ic_launcher_background)
                    // transition when placeholder -> image
                    crossfade(true)
                    error(R.drawable.ic_launcher_foreground)
                }
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(48.dp))
                    .padding(top = 5.dp, bottom = 5.dp)
            )

            Column() {
                Text(animal.nombre, modifier = Modifier.weight(0.2f))
                Text(animal.raza, modifier = Modifier.weight(0.2f))
                Text(animal.edad.toString(), modifier = Modifier.weight(0.2f))
            }
        }
    }

}