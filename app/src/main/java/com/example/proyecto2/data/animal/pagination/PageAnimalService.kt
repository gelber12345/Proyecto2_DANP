package com.example.proyecto2.data.animal.pagination

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto2.data.animal.*
import kotlinx.coroutines.delay


class PageAnimalService(val totalCount: Int, val pageSize: Int, val viewmodel: AnimalDao) {

    //private val list : List<Animal>? = viewmodel.getAllDataAnimal().value

    suspend fun getAnimals(page: Int): List<Animal> {

        val startIndex = (page - 1) * pageSize + 1
        var endIndex = startIndex + pageSize - 1
        if (endIndex > totalCount) {
            endIndex = totalCount
        }
        return (startIndex..endIndex).map {
                index -> viewmodel.findOneAnimal(index)
                //Log.d("INDEX " , " $index")
                // Log.d("INDEX " , " ${viewmodel.findOneAnimal(index).nombre}")
        }

    }

}