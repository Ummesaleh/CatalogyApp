package com.states.catalogyapp.data

import com.states.catalogyapp.data.model.Product
import com.states.catalogyapp.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

//API INTERFACE
interface ProductApi {
    // get request to fetch my products from api
    @GET("products")
    suspend fun getProducts(): ProductResponse

    // get request to fetch a single prod by id
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}