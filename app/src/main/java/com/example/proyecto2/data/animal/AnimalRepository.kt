package com.example.proyecto2.data.animal

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*


class AnimalRepository(application: Application) {
    var animalDao: AnimalDao
    val searchResults = MutableLiveData<List<Animal>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        val database = AnimalDb.getDatabase(application)
        animalDao = database.animalDao()
    }

    val readAllAnimal: LiveData<List<Animal>> = animalDao.getAllDataAnimal()


    suspend fun insertAnimal(animal: Animal) {
        animalDao.insertAnimal(animal)
    }

    suspend fun updateAnimal(centro: Animal) {
        animalDao.updateAnimal(centro)
    }

    suspend fun deleteAnimalById(id: Int) {
        animalDao.deleteAnimalById(id)
    }

    suspend fun deleteAllAnimal() {
        animalDao.deleteAllAnimal()
    }

    fun findAnimal(text: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(text).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Animal>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async animalDao.findAnimal(name)
        }
}