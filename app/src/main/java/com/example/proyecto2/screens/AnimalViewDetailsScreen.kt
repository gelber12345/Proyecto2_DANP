package com.example.proyecto2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.proyecto2.R
import com.example.proyecto2.data.animal.Animal
import com.example.proyecto2.data.animal.AnimalViewModel
import com.example.proyecto2.navigation.AppScreens


@Composable
fun ViewAnimalDetailsScreen(
    navController: NavController,
    animal: Animal,
    viewModel: AnimalViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(32.dp),
            elevation = 5.dp,

            ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
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

                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .padding(5.dp),
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = animal.name.toString(), fontWeight = FontWeight.Bold, fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Column() {
                    Row(modifier = Modifier.padding(4.dp)) {
                        Text("Type: ", fontWeight = FontWeight.Bold)
                        animal.type?.let { Text(it) }
                    }
                    Row(modifier = Modifier.padding(4.dp)) {
                        Text("Specie: ", fontWeight = FontWeight.Bold)
                        animal.specie?.let { Text(it) }
                    }
                    Row(modifier = Modifier.padding(4.dp)) {
                        Text("Age: ", fontWeight = FontWeight.Bold)
                        animal.age?.let { Text(it) }
                    }
                    Row(modifier = Modifier.padding(4.dp)) {
                        Text("Gender: ", fontWeight = FontWeight.Bold)
                        animal.gender?.let { Text(it) }
                    }
                    Row(modifier = Modifier.padding(4.dp)) {
                        Text("Size: ", fontWeight = FontWeight.Bold)
                        animal.size?.let { Text(it) }
                    }
                    Row(modifier = Modifier.padding(4.dp)) {
                        Text("Status: ", fontWeight = FontWeight.Bold)
                        animal.status?.let { Text(it) }
                    }
                    Row(modifier = Modifier.padding(4.dp)) {
                        Text("Published at: ", fontWeight = FontWeight.Bold)
                        animal.published_at?.let { Text(it) }
                    }
                    Row(modifier = Modifier.padding(4.dp)) {
                        Text("Contact: ", fontWeight = FontWeight.Bold)
                        animal.contact?.let { Text(it) }
                    }
                    Row(modifier = Modifier.padding(4.dp)) {
                        Text("Country: ", fontWeight = FontWeight.Bold)
                        animal.country?.let { Text(it) }
                    }
                    Button(
                        onClick = {
                            navController.navigate(AppScreens.AnimalScreen.route)
                        },
                        shape = RoundedCornerShape(20.dp),
                        elevation = ButtonDefaults.elevation(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 24.dp,
                                bottom = 24.dp,
                                start = 48.dp,
                                end = 48.dp
                            ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White,
                            backgroundColor = Color(18, 114, 163)
                        )
                    ) {
                        Text(
                            text = "Ok",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}
