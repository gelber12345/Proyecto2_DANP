package com.example.proyecto2.data.animal

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animal")
    fun getAllDataAnimal(): LiveData<List<Animal>>

    @Query(
        "SELECT * FROM animal WHERE Nombre LIKE :search " +
                "or Raza LIKE :search " +
                "or Edad LIKE :search "

    )
    fun findAnimal(search: String): List<Animal>


    @Query("SELECT * FROM animal WHERE id = :id")
    suspend fun findOneAnimal(id: Int): Animal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: Animal)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAnimal(entity: Animal)

    @Query("DELETE FROM animal where id = :id")
    suspend fun deleteAnimalById(id: Int)

    @Query("DELETE FROM animal")
    suspend fun deleteAllAnimal()
}