package com.example.proyecto2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.paging.compose.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.proyecto2.R
import com.example.proyecto2.data.Data
import com.example.proyecto2.data.animal.Animal
import com.example.proyecto2.data.animal.AnimalViewModel
import com.example.proyecto2.data.animal.pagination.PageAnimalVM
import com.example.proyecto2.navigation.AppScreens
import com.example.proyecto2.screens.components.CustomTextField
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
        var filter by remember { mutableStateOf("") }
        val onTextChange = { text: String ->
            textBusqueda = text
        }

        Text(text = "Buscar una mascota", fontSize = 20.sp )

        OutlinedTextField(
            value = textBusqueda,
            onValueChange = {textBusqueda = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            label = { Text("Busqueda") },
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
            leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Botón para elegir fecha"
                    )
            }
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
                    searching = true
                    filter = "gato"
                    viewModel.findAnimal("%$filter%")
                    //navController.navigate(AppScreens.EditAnimalScreen.route)
                    //navController.previousBackStackEntry?.savedStateHandle?.remove<Animal>("animal")
                }) {
                Column() {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "GATOS"
                    )
                    Text("GATOS",fontSize = textSize.toInt().sp)
                }

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

            }) {
                Text("TODO")
            }
        }
        //////////////
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {


            item {
                TitleRow(head1 = "Id", head2 = "Nombre", head3 = "Raza")
            }
            if ( searching ){
                items(searchResults) { centro ->
                    AnimalRow(centro, navController, viewModel)
                    Spacer(Modifier.height(10.0.dp))
                }
            }else{
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
fun AnimalRow(animal: Animal, navController: NavController, viewModel: AnimalViewModel) {
    Card(elevation = 20.dp,
        shape= RoundedCornerShape(20.dp),) {
        Row(
            modifier = Modifier
                .fillMaxWidth().height(120.dp)
                .padding(5.dp)
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
                 modifier = Modifier.size(120.dp),

             )

            Column() {
                Text(animal.nombre, modifier = Modifier.weight(0.2f), color = Color.Black)
                Text(animal.raza, modifier = Modifier.weight(0.2f))
                Text(animal.edad.toString(), modifier = Modifier.weight(0.2f))
            }
        }
    }

}