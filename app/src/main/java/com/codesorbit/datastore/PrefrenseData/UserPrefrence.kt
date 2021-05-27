package com.codesorbit.datastore.PrefrenseData

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class UserPrefrence(context: Context) {
    private val dataStore: DataStore<Preferences>
    private val application_Context = context.applicationContext

    init {
        dataStore = application_Context.createDataStore(name = "app_Preference")
    }

    val bookmarks: Flow<String> = dataStore.data.catch {
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }
        .map { preferences ->
            preferences[BOOKMARKKEY] ?: "Not Found"
        }
    val booleanValue: Flow<Boolean> = dataStore.data.catch {
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map { preferences ->
        preferences[VALUE] ?: false
    }

    suspend fun saveBookMarks(bookmarks: String, value: Boolean) {
        dataStore.edit { preference ->
            preference[BOOKMARKKEY] = bookmarks
            preference[VALUE] = value
        }
    }

    companion object {
        val BOOKMARKKEY = preferencesKey<String>("key_bookmark")
        val VALUE = preferencesKey<Boolean>("key_value")
    }
}