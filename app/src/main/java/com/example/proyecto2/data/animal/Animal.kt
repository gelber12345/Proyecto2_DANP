package com.example.proyecto2.data.animal

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "animal")
class Animal(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "Nombre")
    val nombre: String,
    @ColumnInfo(name = "Raza")
    val raza: String,
    @ColumnInfo(name = "Edad")
    val edad: Int,

) : Parcelable