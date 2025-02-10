package top.vitafresh.urecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){

    private val _categoryState = mutableStateOf(RecipeState())
    val categoriesState : State<RecipeState> = _categoryState

    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            try{
                val response = recipeSerice.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            }
            catch (e: Exception){
                println(e)
                _categoryState.value =_categoryState.value.copy(
                    loading = false,
                    error = "Error fethcing Catagories ${e.message}"
                )
            }
        }
    }


    data class RecipeState(
        val loading: Boolean = false,
        val list : List<Category> = emptyList(),
        val error: String? = null
    )
    
}