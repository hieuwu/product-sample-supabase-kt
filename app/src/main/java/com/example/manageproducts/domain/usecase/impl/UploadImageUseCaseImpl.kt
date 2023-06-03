package com.example.manageproducts.domain.usecase.impl

import com.example.manageproducts.data.repository.ImageRepository
import com.example.manageproducts.domain.usecase.UploadImageUseCase
import javax.inject.Inject

class UploadImageUseCaseImpl @Inject constructor(
    private val imageRepository: ImageRepository
): UploadImageUseCase {
    override suspend fun execute(input: UploadImageUseCase.Input): UploadImageUseCase.Output {
        imageRepository.uploadImage(input.imageByteArray)
        return UploadImageUseCase.Output.Success
    }
}