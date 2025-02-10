package top.vitafresh.urecipeapp

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String
)


data class CategoryResponse(val categories: List<Category>) {

}