package com.example.proyecto2.Firebase

import android.content.ContentValues
import android.util.Log
import com.example.proyecto2.data.animal.Animal
import com.example.proyecto2.data.animal.AnimalViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseAPI {
    val db = Firebase.firestore

    fun updateData(viewModel: AnimalViewModel) {
        viewModel.deleteAllAnimal()

        db.collection("animales")
            .get()
            .addOnSuccessListener { result ->
                for (register in result) {
                    viewModel.insertAnimal(
                        Animal(
                            0,
                            register.data["type"].toString(),
                            register.data["species"].toString(),
                            register.data["age"].toString(),
                            register.data["gender"].toString(),
                            register.data["size"].toString(),
                            register.data["name"].toString().uppercase(),
                            register.data["status"].toString(),
                            register.data["published_at"].toString(),
                            register.data["contact"].toString(),
                            register.data["country"].toString(),
                            register.data["photo"].toString()
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }


}