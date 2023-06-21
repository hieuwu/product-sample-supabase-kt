package com.example.manageproducts.domain.usecase

interface AuthenticateUseCase : UseCase<AuthenticateUseCase.Input, AuthenticateUseCase.Output> {
    class Input(val email: String, val password: String)
    sealed class Output() {
        object Success : Output()
        object Failure : Output()
    }
}