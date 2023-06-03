package com.example.manageproducts.data.repository.impl

import com.example.manageproducts.data.repository.ImageRepository
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.upload
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val storage: Storage
) : ImageRepository {
    override suspend fun uploadImage(image: ByteArray) {
        storage["Product%20Image"].upload(
            "newimage.png", image,
            upsert = true
        )
    }

    override suspend fun getImage() {
        TODO("Not yet implemented")
    }
}