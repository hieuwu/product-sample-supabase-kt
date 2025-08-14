package com.example.manageproducts.presentation.feature.deeplink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageproducts.data.repository.AuthenticationRepository
import com.example.manageproducts.presentation.feature.deeplink.state.DeepLinkHandlerState
import com.example.manageproducts.presentation.feature.deeplink.state.RedirectDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DeepLinkHandlerViewModel @Inject constructor(
    private val authRepository: AuthenticationRepository
): ViewModel() {

    private val _state = MutableStateFlow(DeepLinkHandlerState())
    val state: StateFlow<DeepLinkHandlerState> = _state.asStateFlow()

    fun verifyGoogleAuth(code: String) {
        viewModelScope.launch {
            authRepository.exchangeCodeForSession(code).fold(
                onSuccess = {
                    _state.value =
                        _state.value.copy(
                            redirectDestination = RedirectDestination.EmailConfirmation
                        )
                },
                onFailure = {

                }
            )
        }
    }

    fun verifyEmailConfirmation(token: String) {
        viewModelScope.launch {
            authRepository.verifyEmail(token).fold(
                onSuccess = {
                    _state.value =
                        _state.value.copy(redirectDestination = RedirectDestination.EmailConfirmation)
                },
                onFailure = {
                    _state.value =
                        _state.value.copy(redirectDestination = RedirectDestination.EmailConfirmation)
                }
            )
        }
    }
}