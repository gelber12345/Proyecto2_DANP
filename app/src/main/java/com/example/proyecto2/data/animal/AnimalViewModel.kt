package com.example.proyecto2.data.animal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.proyecto2.data.animal.pagination.PageAnimalService
import com.example.proyecto2.data.animal.pagination.PageAnimalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AnimalViewModel(appObj: Application) : AndroidViewModel(appObj) {

    private val centroRepository: AnimalRepository = AnimalRepository(appObj)

    val searchResults: MutableLiveData<List<Animal>> = centroRepository.searchResults


    val animalFlow = fetchBloodData()

    fun fetchBloodData(): Flow<PagingData<Animal>> {
        val repo = PageAnimalService(totalCount = 99, pageSize = 5,centroRepository.animalDao )
        return Pager(PagingConfig(pageSize = repo.pageSize)) {
            PageAnimalSource(repo)
        }.flow
    }

    fun fetchAllAnimal(): LiveData<List<Animal>> {
        return centroRepository.readAllAnimal
    }

    fun insertAnimal(animal: Animal) {
        viewModelScope.launch {
            centroRepository.insertAnimal( animal)
        }

    }

    fun findAnimal(text: String) {
        viewModelScope.launch {
            centroRepository.findAnimal(text)
        }

    }

    fun updateAnimal(animal: Animal) {
        viewModelScope.launch {
            centroRepository.updateAnimal(animal)
        }

    }

    fun deleteAnimalById(id: Int) {
        viewModelScope.launch {
            centroRepository.deleteAnimalById(id)
        }
    }

    fun deleteAllAnimal() {
        viewModelScope.launch {
            centroRepository.deleteAllAnimal()
        }
    }

}