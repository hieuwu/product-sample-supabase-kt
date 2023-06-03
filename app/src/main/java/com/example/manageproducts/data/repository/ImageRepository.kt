package com.example.manageproducts.data.repository

interface ImageRepository {
    suspend fun uploadImage(image: ByteArray)
    suspend fun getImage()
}