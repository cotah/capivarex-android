package com.capivarex.android.data.api

import com.capivarex.android.data.model.*
import retrofit2.http.*

interface CapivarexApi {

    // ── Health ────────────────────────────────────────────────
    @GET("/api/health")
    suspend fun health(): HealthResponse

    // ── User ─────────────────────────────────────────────────
    @GET("/api/webapp/user/me")
    suspend fun getMe(): UserProfile

    // ── Chat ─────────────────────────────────────────────────
    @POST("/api/webapp/chat")
    suspend fun sendMessage(@Body request: ChatRequest): ChatResponse

    @GET("/api/webapp/conversations")
    suspend fun getConversations(): ConversationsResponse

    @GET("/api/webapp/conversations/{id}/messages")
    suspend fun getMessages(
        @Path("id") conversationId: String,
        @Query("limit") limit: Int = 50,
    ): List<ChatMessage>
}
