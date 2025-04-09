package com.states.catalogyapp.data

import com.states.catalogyapp.data.model.Product
import com.states.catalogyapp.data.model.Result

class ProductRepository(private val api: ProductApi) {
    suspend fun getProducts(): Result<List<Product>> {
        return try {
            val response = api.getProducts()
            Result.Success(response.products)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    suspend fun getProductById(id: Int): Result<Product> {
        return try {
            val response = api.getProductById(id)  //
            Result.Success(response)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}