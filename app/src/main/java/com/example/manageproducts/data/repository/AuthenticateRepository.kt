package com.example.manageproducts.data.repository

interface AuthenticateRepository {
    suspend fun logIn(email: String, password: String): Boolean
    suspend fun signUp(email: String, password: String): Boolean
}