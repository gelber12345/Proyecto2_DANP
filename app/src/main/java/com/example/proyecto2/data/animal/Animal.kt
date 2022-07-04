package com.example.proyecto2.data.animal

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "animals")
class Animal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "type") val type: String? = "",
    @ColumnInfo(name = "specie") val specie: String? = "",
    @ColumnInfo(name = "age") val age: String? = "",
    @ColumnInfo(name = "gender") val gender: String? = "",
    @ColumnInfo(name = "size") val size: String? = "",
    @ColumnInfo(name = "name") val name: String? = "",
    @ColumnInfo(name = "status") val status: String? = "",
    @ColumnInfo(name = "published_at") val published_at: String? = "",
    @ColumnInfo(name = "contact") val contact: String? = "",
    @ColumnInfo(name = "country") val country: String? = "",
    @ColumnInfo(name = "photo") val photo: String? = ""
) : Parcelable