package com.example.proyecto2.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.proyecto2.data.Data
import kotlinx.coroutines.launch
import com.example.proyecto2.R
import kotlinx.coroutines.flow.collect

@Composable
fun ConfigScreen (navController: NavController) {

    var textSize by rememberSaveable { mutableStateOf("0") }
    var size by rememberSaveable { mutableStateOf("0") }
    var color by rememberSaveable{ mutableStateOf("0") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Data(context)

    LaunchedEffect(scope) {
        dataStore.getColor.collect { data ->
            color = data.toString()
        }
    }
    LaunchedEffect(scope){
        dataStore.getSize.collect {
                data -> textSize = data.toString()
            size = data.toString()
        }
    }

    var mExpanded by remember { mutableStateOf(false) }

    // Create a list of cities
    val mColors = listOf("ROJO", "NARANJA", "AZUL")

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Box (
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(color.toInt()))
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Text(
                text = "Configuracion",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )


            Spacer(Modifier.height(48.dp))


            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .background(Color(color.toInt())),

                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    Modifier.background(Color(231, 235, 230)),
                    horizontalAlignment = Alignment.CenterHorizontally)

                {
                    Spacer(Modifier.height(16.dp))

                    Row() {

                        Icon(
                            painterResource(id = R.drawable.fuente ),
                            contentDescription = "Fuente",
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp).weight(0.5f),
                            tint = Color.Unspecified
                        )

                        Text(
                            modifier = Modifier
                                .padding(16.dp, 0.dp)
                                .alpha(0.6f).weight(1f),
                            text = "Tamaño ",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                            fontSize = textSize.toInt().sp
                        )
                        //email field
                        OutlinedTextField(

                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType
                                = KeyboardType.Number
                            ),

                            value = size,
                            onValueChange = { size = it },
                            modifier = Modifier
                                .padding(16.dp, 0.dp, 16.dp, 0.dp)
                                .fillMaxWidth().weight(2f),

                            )
                    }

                    Spacer(Modifier.height(16.0.dp))

                    Row() {

                        Icon(
                            painterResource(id = R.drawable.color ),
                            contentDescription = "Color",
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp).weight(1f),
                            tint = Color.Unspecified
                        )

                        OutlinedTextField(
                            value = mSelectedText,
                            onValueChange = { mSelectedText = it },
                            modifier = Modifier
                                .fillMaxWidth().padding(16.dp, 0.dp, 16.dp, 0.dp).weight(2f),
                            label = { Text("Color") },
                            trailingIcon = {
                                Icon(icon,"contentDescription",
                                    Modifier.clickable { mExpanded = !mExpanded })
                            }
                        )

                        // Create a drop-down menu with list of cities,
                        // when clicked, set the Text Field text as the city selected
                        DropdownMenu(
                            expanded = mExpanded,
                            onDismissRequest = { mExpanded = false },
                        ) {
                            mColors.forEach { label ->
                                DropdownMenuItem(onClick = {
                                    mSelectedText = label
                                    mExpanded = false
                                }) {
                                    Text(text = label)
                                }
                            }
                        }

                    }

                    Spacer(Modifier.height(16.0.dp))

                    Button(
                        onClick = {
                            scope.launch {

                                dataStore.saveSize(size.toFloat().toInt())
                                if (mSelectedText == "ROJO"){
                                    dataStore.saveColor(Color(238, 74, 57).toArgb())
                                }else if(mSelectedText == "NARANJA"){
                                    dataStore.saveColor(Color(245, 161, 76).toArgb())

                                }else if(mSelectedText == "AZUL"){
                                    dataStore.saveColor(Color(18, 114, 163).toArgb())
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(16.dp, 0.dp, 16.dp, 0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White,
                            backgroundColor =Color(color.toInt())
                        )
                    ) {
                        Text(
                            style = MaterialTheme.typography.subtitle1,
                            color = Color.White,
                            text = "GUARDAR",
                            fontSize = textSize.toInt().sp
                        )
                    }

                    Spacer(Modifier.height(16.0.dp))

                }
            }

        }
    }
}
