package com.example.proyecto2.data.animal.pagination

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.proyecto2.data.animal.Animal
import com.example.proyecto2.data.animal.AnimalRepository
import kotlinx.coroutines.flow.Flow

class PageAnimalVM (appObj: Application) : AndroidViewModel(appObj) {
    /*val animalFlow = fetchBloodData()

    fun fetchBloodData(): Flow<PagingData<Animal>> {
        val repo = PageAnimalService(
            totalCount = 99, pageSize = 5,
            AnimalRepository(this.getApplication())
        )
        return Pager(PagingConfig(pageSize = repo.pageSize)) {
            PageAnimalSource(repo)
        }.flow
    }*/
}