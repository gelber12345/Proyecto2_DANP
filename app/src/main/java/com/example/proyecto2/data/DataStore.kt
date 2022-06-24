package com.example.proyecto2.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class Data(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("configuracion")

        val USER_COLOR_KEY = intPreferencesKey("user_color")
        val USER_TEXTSIZE_KEY = intPreferencesKey("user_size")
    }


    //get the saved Size
    val getSize: Flow<Int> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_TEXTSIZE_KEY] ?: 12
        }
    //save Size into datastore
    suspend fun saveSize(data: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_TEXTSIZE_KEY] = data
        }
    }
    //get the saved Duracion
    val getColor: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_COLOR_KEY] ?: Color.DarkGray.toArgb()
        }

    //save Duracion into datastore
    suspend fun saveColor(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_COLOR_KEY] = name
        }
    }


}