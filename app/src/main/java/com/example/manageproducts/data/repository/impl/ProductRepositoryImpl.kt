package com.example.manageproducts.data.repository.impl

import com.example.manageproducts.data.network.dto.ProductDto
import com.example.manageproducts.data.repository.ProductRepository
import com.example.manageproducts.domain.model.Product
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
) : ProductRepository {
    override suspend fun createProduct(product: Product): Boolean {
        return try {
            val productDto = ProductDto(
                name = product.name,
                price = product.price,
            )
            postgrest["products"].insert(productDto)
            true
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    override suspend fun getProducts(): List<ProductDto>? {
        val result = postgrest["products"]
            .select().decodeList<ProductDto>()
        return result
    }


    override suspend fun getProduct(id: String): ProductDto {
        return postgrest["products"].select {
                eq("id", id)
            }.decodeSingle<ProductDto>()
    }

    override suspend fun deleteProduct(id: String) {
        postgrest["products"].delete {
            eq("id", id)
        }
    }

    override suspend fun updateProduct(id: String,name: String, price: Double) {
        postgrest["products"].update({
            set("name", name)
            set("price", price)
        }) {
            eq("id", id)
        }
    }
}