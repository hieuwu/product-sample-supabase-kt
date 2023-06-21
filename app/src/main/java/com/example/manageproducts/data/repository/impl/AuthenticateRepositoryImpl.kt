package com.example.manageproducts.data.repository.impl

import com.example.manageproducts.data.repository.AuthenticateRepository
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import javax.inject.Inject

class AuthenticateRepositoryImpl @Inject constructor(
    private val authService: GoTrue
) : AuthenticateRepository {
    override suspend fun logIn(email: String, password: String): Boolean {
        authService.loginWith(Email) {
            this.email = email
            this.password = password
        }
        return true
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        authService.signUpWith(Email) {
            this.email = email
            this.password = password
        }
        return true
    }
}