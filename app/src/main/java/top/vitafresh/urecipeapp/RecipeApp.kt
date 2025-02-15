package top.vitafresh.urecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(Screen.RecipeScreen.route) {
            RecipeScreen(
                viewState = viewState,
                navigateToDetail = {
                    // This part is responsible for passing the data
                    // from the current RecipeScreen to the CategoryDetailScreen
                    // It utilizes the savedStateHandle, which is a component of the Compose navigation framework
                    // to pass the data
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                    navController.navigate(Screen.CategoryDetailScreen.route)
                }
            )
        }

        composable(Screen.CategoryDetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.
                get<Category>("cat") ?: Category("", "", "", "")
                CategoryDetailScreen(category = category)
        }
    }
}

