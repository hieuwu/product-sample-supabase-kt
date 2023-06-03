package com.example.manageproducts.domain.usecase

interface UploadImageUseCase : UseCase<UploadImageUseCase.Input, UploadImageUseCase.Output> {
    class Input(val imageByteArray: ByteArray)
    sealed class Output {
        object Success: Output()
    }
}