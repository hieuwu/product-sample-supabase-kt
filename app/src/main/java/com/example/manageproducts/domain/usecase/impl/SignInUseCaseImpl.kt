package com.example.manageproducts.domain.usecase.impl

import com.example.manageproducts.data.repository.AuthenticateRepository
import com.example.manageproducts.domain.usecase.AuthenticateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticateUseCaseImpl @Inject constructor(
    private val authenticateRepository: AuthenticateRepository
) : AuthenticateUseCase {
    override suspend fun execute(input: AuthenticateUseCase.Input): AuthenticateUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = authenticateRepository.logIn(input.email, input.password)
            if (result) {
                AuthenticateUseCase.Output.Success
            } else {
                AuthenticateUseCase.Output.Failure
            }
        }
    }
}