package com.example.manageproducts.domain.model

sealed interface AuthState {
    data object Initializing : AuthState
    data object Authenticated : AuthState
    data object Unauthenticated: AuthState
}