package com.example.proyecto2.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.proyecto2.data.Data
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@Composable
fun ConfigScreen (navController: NavController) {
    var textSize by rememberSaveable { mutableStateOf("0") }
    var size by rememberSaveable { mutableStateOf("0") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Data(context)

    var mExpanded by remember { mutableStateOf(false) }

    // Create a list of cities
    val mCities = listOf("RED", "MORADO", "BLUE")

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier = Modifier
            .wrapContentSize()
            .verticalScroll(rememberScrollState())

    ) {

        LaunchedEffect(scope){
            dataStore.getSize.collect {
                    data -> textSize = data.toString()
                size = data.toString()
            }
        }

        Text(
            modifier = Modifier
                .padding(16.dp, 0.dp)
                .alpha(0.6f),
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
                .fillMaxWidth(),

            )

        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text("Label") },
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
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }

        Button(
            onClick = {
                scope.launch {

                    dataStore.saveSize(size.toFloat().toInt())

                    if (mSelectedText == "RED"){
                        dataStore.saveColor(Color.Red.toArgb())
                        Log.d("RED" , "${Color.Red.toArgb()}")
                        //Log.d("RED" , "${}")
                    }else if(mSelectedText == "MORADO"){
                        dataStore.saveColor(Color.DarkGray.toArgb())
                        Log.d("RED" , "${Color.DarkGray.toArgb()}")
                    }else if(mSelectedText == "BLUE"){
                        dataStore.saveColor(Color.Blue.toArgb())
                        Log.d("RED" , "${Color.Blue.toArgb()}")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(16.dp, 0.dp, 16.dp, 0.dp),
        ) {
            Text(
                style = MaterialTheme.typography.subtitle1,
                color = Color.White,
                text = "GUARDAR",
                fontSize = textSize.toInt().sp
            )
        }


    }

}
