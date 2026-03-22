package com.capivarex.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val plan: String = "professional",
    val language: String = "en",
    @SerialName("phone_number") val phoneNumber: String? = null,
    @SerialName("messages_used") val messagesUsed: Int = 0,
    @SerialName("messages_limit") val messagesLimit: Int = 30,
    val modules: List<String> = listOf("ara"),
)

@Serializable
data class ChatMessage(
    val id: String = "",
    val role: String = "user", // "user" | "assistant"
    val content: String = "",
    @SerialName("created_at") val createdAt: String = "",
)

@Serializable
data class ChatRequest(
    val message: String,
    @SerialName("conversation_id") val conversationId: String? = null,
)

@Serializable
data class ChatResponse(
    val response: String = "",
    @SerialName("conversation_id") val conversationId: String = "",
)

@Serializable
data class Conversation(
    val id: String = "",
    val title: String = "",
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = "",
    @SerialName("message_count") val messageCount: Int = 0,
)

@Serializable
data class ConversationsResponse(
    val conversations: List<Conversation> = emptyList(),
)

@Serializable
data class HealthResponse(
    val status: String = "",
    val version: String = "",
)
