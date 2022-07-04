package com.example.proyecto2.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Data(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("configuracion")

        val USER_COLOR_KEY = intPreferencesKey("user_color")
        val USER_TEXTSIZE_KEY = intPreferencesKey("user_size")
        val USER_PASSWORD_KEY = stringPreferencesKey("user_password")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val USER_DATABASE_KEY = intPreferencesKey("user_database")
        val USER_SEARCH_KEY = stringPreferencesKey("user_search")
    }


    //get the saved Size
    val getSize: Flow<Int> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_TEXTSIZE_KEY] ?: 16
        }

    //save Size into datastore
    suspend fun saveSize(data: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_TEXTSIZE_KEY] = data
        }
    }

    //get the Color Duracion
    val getColor: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_COLOR_KEY] ?: Color(18, 114, 163).toArgb()
        }

    //save Color into datastore
    suspend fun saveColor(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_COLOR_KEY] = name
        }
    }

    //get the PASSWORD Size
    val getPassword: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_PASSWORD_KEY] ?: "123"
        }

    //save PASSWORD into datastore
    suspend fun savePassword(data: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PASSWORD_KEY] = data
        }
    }

    //get the EMAIL Size
    val getEmail: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_EMAIL_KEY] ?: "example@hotmail.com"
        }

    //save EMAIL into datastore
    suspend fun saveEmail(data: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = data
        }
    }

    //get the BACKGROUND Duracion
    val getDatabase: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_DATABASE_KEY] ?:  0
        }

    //save BACKGROUND into datastore
    suspend fun saveDatabase(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_DATABASE_KEY] = name
        }
    }

    //get the SEARCH Size
    val getSearch: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_SEARCH_KEY] ?: ""
        }

    //save SEARCH into datastore
    suspend fun saveSearch(data: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_SEARCH_KEY] = data
        }
    }
}