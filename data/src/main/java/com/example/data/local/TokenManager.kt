package com.example.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

class TokenManager(
    context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = token
        }
    }

    fun getAccessTokenSync(): String? = runBlocking {
        dataStore.data.firstOrNull()?.get(ACCESS_TOKEN_KEY)
    }

    suspend fun getAccessToken(): String? {
        return dataStore.data.firstOrNull()?.get(ACCESS_TOKEN_KEY)
    }

    suspend fun clearToken() {
        dataStore.edit { it.clear() }
    }
}