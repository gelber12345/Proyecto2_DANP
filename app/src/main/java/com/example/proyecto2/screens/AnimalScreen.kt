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
import com.example.proyecto2.navigation.AppScreens
import kotlinx.coroutines.launch

@Composable
fun AnimalScreen(
    navController: NavController,
    viewModel: AnimalViewModel
) {
    val allAnimals: LazyPagingItems<Animal> = viewModel.animalFlow.collectAsLazyPagingItems()
    //val allCentros = pageAnimalviewmodel.animalFlow.collectAsLazyPagingItems()
    val searchResults by viewModel.searchResults.observeAsState(listOf())



    FirstMainScreen(
        allAnimals = allAnimals,
        searchResults = searchResults,
        viewModel = viewModel,
        navController = navController
    )
}

@Composable
fun FirstMainScreen(
    allAnimals: LazyPagingItems<Animal>,
    searchResults: List<Animal>,
    viewModel: AnimalViewModel,
    navController: NavController
) {
    var busqueda by rememberSaveable{ mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Data(context)


    LaunchedEffect(scope) {
        dataStore.getSearch.collect { data ->
            busqueda = data
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        var searching by remember { mutableStateOf(false) }
        var filter by remember { mutableStateOf("") }


        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Buscar una mascota",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Row() {
            OutlinedTextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                label = { Text("Buscar") },
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier.fillMaxWidth().weight(2f),
                maxLines = 1
            )

            Spacer(modifier = Modifier.width(4.dp))
            Button(
                colors = buttonColors(
                    backgroundColor = Color(246, 246, 246),
                ),
                onClick = {
                    searching = true
                    filter = "All"
                    viewModel.findAnimal("%$busqueda%")

                    scope.launch {
                        dataStore.saveSearch(busqueda)
                    }

                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(4.dp).weight(1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "searchIcon",
                        modifier = Modifier
                                .height(40.dp)
                            .width(40.dp),
                        tint = Color.Unspecified
                    )
                }
            }

        }


        Spacer(modifier = Modifier.height(8.dp))

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
                    filter = "Cat"
                    viewModel.findAnimalBySpecie("%$filter%")
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
                    filter = "Dog"
                    viewModel.findAnimalBySpecie("%$filter%")
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
                    searching = true
                    filter = "Bird"
                    viewModel.findAnimalBySpecie("%$filter%")

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
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(id = R.drawable.all),
                        contentDescription = "Todos",
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp),
                        tint = Color.Unspecified
                    )
                    Text("Todos", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            if (searching && filter!=""){

                items(searchResults) { animal ->
                    animal.let {
                        AnimalRow(animal, navController)
                        Spacer(Modifier.height(10.0.dp))
                    }
                }
            }else{
                items(allAnimals) { animal ->
                    animal?.let {
                        AnimalRow(animal, navController)
                        Spacer(Modifier.height(10.0.dp))
                    }
                }
            }


        }
    }
}

@Composable
fun AnimalRow(animal: Animal, navController: NavController) {
    Card(
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(240, 240, 240),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .height(120.dp)
                .clickable {
                    navController.currentBackStackEntry?.savedStateHandle?.set("animal", animal)
                    navController.navigate(AppScreens.ViewDetailsAnimalScreen.route)
                }
        ) {
            val pathImg: Any

            if (animal.type == "Dog") {
                pathImg = R.drawable.dog_default
            } else if (animal.type == "Cat") {
                pathImg = R.drawable.cat_default
            } else {
                pathImg = R.drawable.bird_default
            }

            val painter = rememberImagePainter(
                data = animal.photo,
                builder = {
                    placeholder(pathImg)
                    crossfade(true)
                    error(pathImg)
                }
            )

            Row(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .padding(5.dp),
                )

                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    animal.name?.let {
                        Text(it, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }

                    Row() {
                        Text("Type: ", fontWeight = FontWeight.Bold)
                        animal.type?.let { Text(it) }
                    }

                    Row() {
                        Text("Specie: ", fontWeight = FontWeight.Bold)
                        animal.specie?.let { Text(it) }
                    }

                    Row() {
                        Text("Age: ", fontWeight = FontWeight.Bold)
                        animal.age?.let { Text(it) }
                    }

                    Row() {
                        Text("Gender: ", fontWeight = FontWeight.Bold)
                        animal.gender?.let { Text(it) }
                    }

                    Row() {
                        Text("Size: ", fontWeight = FontWeight.Bold)
                        animal.size?.let { Text(it) }
                    }
                }
            }
        }
    }
}