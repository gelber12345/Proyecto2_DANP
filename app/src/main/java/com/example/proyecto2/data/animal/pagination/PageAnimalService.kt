package com.example.proyecto2.data.animal.pagination

import android.util.Log
import com.example.proyecto2.data.animal.Animal
import com.example.proyecto2.data.animal.AnimalDao

class PageAnimalService(val totalCount: Int, val pageSize: Int, val dao: AnimalDao) {

    suspend fun getAnimals(page: Int): List<Animal> {
        val startIndex = (page - 1) * pageSize + 1
        var endIndex = startIndex + pageSize - 1

        if (endIndex > totalCount) {
            endIndex = totalCount
        }

        return (startIndex..endIndex).map { index ->
                dao.findOneAnimal(index)
            }


    }
}