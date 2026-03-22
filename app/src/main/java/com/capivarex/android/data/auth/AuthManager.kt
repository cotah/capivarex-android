package com.capivarex.android.data.auth

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("capivarex_auth")

@Singleton
class AuthManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val supabase: SupabaseClient,
) {
    companion object {
        private val KEY_TOKEN = stringPreferencesKey("access_token")
        private val KEY_REFRESH = stringPreferencesKey("refresh_token")
    }

    /** Observable auth state — null means not logged in */
    val token: Flow<String?> = context.dataStore.data.map { it[KEY_TOKEN] }

    /** Get current token (blocking for interceptor) */
    suspend fun getToken(): String? = context.dataStore.data.first()[KEY_TOKEN]

    /** Login with email + password */
    suspend fun login(email: String, password: String): Result<Unit> = runCatching {
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
        saveSession()
    }

    /** Register new account */
    suspend fun register(name: String, email: String, password: String): Result<Unit> = runCatching {
        supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password
            data = buildJsonObject {
                put("name", kotlinx.serialization.json.JsonPrimitive(name))
            }
        }
        saveSession()
    }

    /** Logout */
    suspend fun logout() {
        runCatching { supabase.auth.signOut() }
        context.dataStore.edit { prefs ->
            prefs.remove(KEY_TOKEN)
            prefs.remove(KEY_REFRESH)
        }
    }

    /** Refresh session if expired */
    suspend fun refreshIfNeeded(): String? {
        return try {
            supabase.auth.refreshCurrentSession()
            saveSession()
            getToken()
        } catch (_: Exception) {
            null
        }
    }

    private suspend fun saveSession() {
        val session = supabase.auth.currentSessionOrNull()
        context.dataStore.edit { prefs ->
            session?.accessToken?.let { prefs[KEY_TOKEN] = it }
            session?.refreshToken?.let { prefs[KEY_REFRESH] = it }
        }
    }

    private fun buildJsonObject(builder: kotlinx.serialization.json.JsonObjectBuilder.() -> Unit) =
        kotlinx.serialization.json.buildJsonObject(builder)
}
