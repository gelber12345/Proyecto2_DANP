package com.example.proyecto2.data.animal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Animal::class], version = 2)

abstract class AnimalDb : RoomDatabase() {
    abstract fun animalDao(): AnimalDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalDb? = null

        fun getDatabase(context: Context): AnimalDb {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalDb::class.java, "animals"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}