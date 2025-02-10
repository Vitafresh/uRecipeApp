package top.vitafresh.urecipeapp

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel(){

    data class RecipeState(
        val loading: Boolean = false,
        val recipeList : List<Category> = emptyList(),
        val error: String? = null
    )
    
}