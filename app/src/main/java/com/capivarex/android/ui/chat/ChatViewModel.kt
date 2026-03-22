package com.capivarex.android.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capivarex.android.data.api.CapivarexApi
import com.capivarex.android.data.model.ChatMessage
import com.capivarex.android.data.model.ChatRequest
import com.capivarex.android.data.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val input: String = "",
    val isSending: Boolean = false,
    val conversationId: String? = null,
    val user: UserProfile? = null,
    val error: String? = null,
)

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val api: CapivarexApi,
) : ViewModel() {

    private val _state = MutableStateFlow(ChatUiState())
    val state: StateFlow<ChatUiState> = _state.asStateFlow()

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            runCatching { api.getMe() }
                .onSuccess { user -> _state.update { it.copy(user = user) } }
        }
    }

    fun onInputChange(text: String) {
        _state.update { it.copy(input = text, error = null) }
    }

    fun send() {
        val text = _state.value.input.trim()
        if (text.isEmpty() || _state.value.isSending) return

        // Add user message optimistically
        val userMsg = ChatMessage(
            id = "local-${System.currentTimeMillis()}",
            role = "user",
            content = text,
        )

        _state.update {
            it.copy(
                messages = it.messages + userMsg,
                input = "",
                isSending = true,
                error = null,
            )
        }

        viewModelScope.launch {
            runCatching {
                api.sendMessage(
                    ChatRequest(
                        message = text,
                        conversationId = _state.value.conversationId,
                    )
                )
            }.onSuccess { response ->
                val assistantMsg = ChatMessage(
                    id = "resp-${System.currentTimeMillis()}",
                    role = "assistant",
                    content = response.response,
                )
                _state.update {
                    it.copy(
                        messages = it.messages + assistantMsg,
                        isSending = false,
                        conversationId = response.conversationId,
                    )
                }
            }.onFailure { e ->
                _state.update {
                    it.copy(
                        isSending = false,
                        error = e.message ?: "Failed to send message",
                    )
                }
            }
        }
    }

    fun newConversation() {
        _state.update {
            it.copy(messages = emptyList(), conversationId = null, input = "", error = null)
        }
    }
}
