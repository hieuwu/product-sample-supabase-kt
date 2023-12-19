package com.example.manageproducts.data.repository.impl

import android.content.Context
import com.example.manageproducts.data.repository.AuthenticationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val goTrue: Auth,
    @ApplicationContext context: Context,
) : AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            goTrue.signInWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            goTrue.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun signInWithGoogle(): Boolean {
        return try {
            goTrue.signInWith(Google)
            true
        } catch (e: Exception) {
            false
        }
    }
}