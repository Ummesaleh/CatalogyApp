package com.states.catalogyapp.screens

import ProductDetailViewModel
import com.states.catalogyapp.data.ProductApi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.states.catalogyapp.data.ProductRepository
import com.states.catalogyapp.data.RetrofitInstance

import com.states.catalogyapp.viewmodel.ProductListViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    private val productApi: ProductApi by lazy { RetrofitInstance.api } // API instance
    private val productRepository: ProductRepository by lazy { ProductRepository(productApi) }

    // Provide Retrofit
    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://your.api.url/") // Replace with your actual base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide ProductApi
    private fun provideProductApi(): ProductApi {
        return provideRetrofit().create(ProductApi::class.java)
    }

    // Provide ProductRepository
    private fun provideRepository(): ProductRepository {
        return ProductRepository(api = provideProductApi())
    }

    // Provide ViewModel factory
    //fun provideViewModelFactory(): ViewModelProvider.Factory {
        //val repository = provideRepository()

        //return object : ViewModelProvider.Factory {
         //   override fun <T : ViewModel> create(modelClass: Class<T>): T {
          //      return ProductListViewModel(repository) as T
            //}
        //}
    //}
//}
        fun provideViewModelFactory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
                        return ProductListViewModel(productRepository) as T
                    }
                    if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
                        return ProductDetailViewModel(productRepository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
}
