package com.example.manageproducts.data.repository

import com.example.manageproducts.domain.model.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthenticationRepository {
    val authState: StateFlow<AuthState>
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun signInWithGoogle(): Boolean
    suspend fun exchangeCodeForSession(code: String): Result<Unit>
    suspend fun verifyEmail(tokenHash: String): Result<Unit>
}