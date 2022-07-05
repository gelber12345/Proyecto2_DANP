package com.example.proyecto2.data.animal

import android.app.Application
import android.util.Log
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
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch

class AnimalViewModel(appObj: Application) : AndroidViewModel(appObj) {

    private val animalRepository: AnimalRepository = AnimalRepository(appObj)
    val searchResults: MutableLiveData<List<Animal>> = animalRepository.searchResults
    val animalFlow = fetchAnimalData()



    fun fetchAnimalData(): Flow<PagingData<Animal>> {
        val repo = PageAnimalService(totalCount =200, pageSize = 5, animalRepository.animalDao)
        return Pager(PagingConfig(pageSize = repo.pageSize)) {
            PageAnimalSource(repo)
        }.flow
    }



    fun insertAnimal(animal: Animal) {
        viewModelScope.launch {
            animalRepository.insertAnimal(animal)
        }
    }

    fun findAnimal(text: String) {
        viewModelScope.launch {
            animalRepository.findAnimal(text)
        }
    }

    fun findAnimalBySpecie(text: String) {
        viewModelScope.launch {
            animalRepository.findAnimalBySpecie(text)
        }
    }
}