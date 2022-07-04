package com.example.proyecto2.data.animal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animals")
    fun getAllDataAnimal(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals WHERE type = :type")
    fun getAllDataAnimalByType(type: String): LiveData<List<Animal>>

   @Query(
       "SELECT * FROM animals WHERE type LIKE :search " +
               "or age LIKE :search " +
               "or size LIKE :search " +
               "or name LIKE :search " +
               "or status LIKE :search " +
               "or country LIKE :search " +
               "or gender LIKE :search "

    )
    fun findAnimal(search: String): List<Animal>

    @Query(
        "SELECT * FROM animals WHERE specie LIKE :search "
    )
    fun findAnimalbBySpecie(search: String): List<Animal>
//
    @Query("SELECT * FROM animals WHERE id = :id")
    suspend fun findOneAnimal(id: Int): Animal
//
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnimal(animal: Animal)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun updateAnimal(entity: Animal)
//
//    @Query("DELETE FROM animal where id = :id")
//    suspend fun deleteAnimalById(id: Int)
//
    @Query("DELETE FROM animals")
    suspend fun deleteAllAnimal()
}