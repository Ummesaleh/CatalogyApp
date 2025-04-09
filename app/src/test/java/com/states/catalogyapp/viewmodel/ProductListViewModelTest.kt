package com.states.catalogyapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.states.catalogyapp.data.ProductRepository
import com.states.catalogyapp.data.model.Product
import com.states.catalogyapp.data.model.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProductListViewModel
    private lateinit var mockRepository: ProductRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        viewModel = ProductListViewModel(mockRepository)
    }

    @Test
    fun `loadProducts should update state with products when successful`() = runTest {
        // Given
        val mockProducts = listOf(Product(id = 1, title = "Test Product")) // Fixed listOf
        coEvery { mockRepository.getProducts() } returns Result.Success(mockProducts) // Fixed Result.Success

        // When
        viewModel.loadProducts()

        // Then
        assertTrue(viewModel.state.value.products == mockProducts)
        assertFalse(viewModel.state.value.isLoading)
        assertNull(viewModel.state.value.error)
    }

    @Test
    fun `loadProducts should update state with error when failed`() = runTest {
        // Given
        val exception = Exception("Test error")
        coEvery { mockRepository.getProducts() } returns Result.Failure(exception) // Fixed Result.Failure

        // When
        viewModel.loadProducts()

        // Then
        assertEquals("Test error", viewModel.state.value.error)
        assertFalse(viewModel.state.value.isLoading)
        assertTrue(viewModel.state.value.products.isEmpty())
    }
}