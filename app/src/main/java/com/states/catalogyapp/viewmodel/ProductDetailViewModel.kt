
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.states.catalogyapp.data.model.Product
import com.states.catalogyapp.data.ProductRepository
import com.states.catalogyapp.data.model.Result
import kotlinx.coroutines.launch


//state fr product detail screen
class ProductDetailViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    private val _state = mutableStateOf(ProductDetailState()) // Internal state
    val state: State<ProductDetailState> = _state // Exposed state

    fun loadProduct(productId: Int) {
        viewModelScope.launch { // Coroutine scope
            _state.value = _state.value.copy(isLoading = true)
            when (val result = repository.getProductById(productId)) {
                is Result.Success -> { // Success case
                    _state.value = _state.value.copy(
                        product = result.data,
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

data class ProductDetailState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
