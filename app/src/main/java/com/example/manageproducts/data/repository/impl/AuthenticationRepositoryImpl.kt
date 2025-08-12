package com.example.manageproducts.data.repository.impl

import android.util.Log
import com.example.manageproducts.data.repository.AuthenticationRepository
import com.example.manageproducts.domain.model.AuthState
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import kotlin.time.ExperimentalTime

private const val logTag = "AuthenticationRepository"
class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth,
) : AuthenticationRepository {

    private val _authState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Initializing)
    override val authState: StateFlow<AuthState> = _authState

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        scope.launch {
            auth.sessionStatus.collect { status ->
                logSessionStatus(status)
            }
        }
    }

    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            auth.signInWith(Email) {
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
            auth.signUpWith(Email) {
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
            auth.signInWith(Google)
            true
        } catch (e: Exception) {
            false
        }
    }


    @OptIn(ExperimentalTime::class)
    private fun logSessionStatus(sessionStatus: SessionStatus) {
        when (sessionStatus) {
            is SessionStatus.Authenticated -> {
                Log.d(
                    logTag, """
                           Session source:${sessionStatus.source}
                           SessionStatus: Authenticated
                           Session expiry:${sessionStatus.session.expiresAt.toLocalDateTime(TimeZone.UTC)}
                    """
                )
                _authState.value = AuthState.Authenticated
            }


            SessionStatus.Initializing -> {
                Log.d(logTag, "SessionStatus: Initializing")
            }


            is SessionStatus.RefreshFailure -> {
                Log.d(logTag, "SessionStatus: RefreshFailure")
            }

            is SessionStatus.NotAuthenticated -> {
                Log.d(
                    logTag,
                    """
                           SessionStatus: NotAuthenticated
                           IsSignOut: ${sessionStatus.isSignOut}
                    """.trimIndent()
                )
            }

        }
    }
}