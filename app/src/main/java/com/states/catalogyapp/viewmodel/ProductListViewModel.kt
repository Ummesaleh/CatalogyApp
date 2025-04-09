package com.states.catalogyapp.viewmodel



import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.states.catalogyapp.data.model.Product
import com.states.catalogyapp.data.ProductRepository
import kotlinx.coroutines.launch

import com.states.catalogyapp.data.model.Result

//state of product list screen
class ProductListViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    private val _state = mutableStateOf(ProductListState()) // internal state
    val state: State<ProductListState> = _state // exposed state

    init {
        loadProducts() // load products on initialization
    }
    fun loadProducts() {
        viewModelScope.launch { // Coroutine scope
            _state.value = _state.value.copy(isLoading = true) // Show loading

            val result = repository.getProducts() // Remove explicit type declaration

            when (result) { // Remove type specification here
                is Result.Success -> { // Success case
                    _state.value = _state.value.copy(
                        products = result.data, // Access data directly
                        isLoading = false,
                        error = null
                    )
                }
                is Result.Failure -> { // Error case
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.exception.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}

data class ProductListState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
