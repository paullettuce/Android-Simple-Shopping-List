package pl.paullettuce.simpleshoppinglist.domain.model

data class ShoppingListDetails(
    val id: Long,
    val name: String,
    val creationDate: String,
    val isActive: Boolean
)