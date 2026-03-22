package com.capivarex.android.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capivarex.android.data.api.CapivarexApi
import com.capivarex.android.data.auth.AuthManager
import com.capivarex.android.data.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsState(
    val user: UserProfile? = null,
    val loggedOut: Boolean = false,
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val api: CapivarexApi,
    private val authManager: AuthManager,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            runCatching { api.getMe() }
                .onSuccess { user -> _state.update { it.copy(user = user) } }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authManager.logout()
            _state.update { it.copy(loggedOut = true) }
        }
    }
}
