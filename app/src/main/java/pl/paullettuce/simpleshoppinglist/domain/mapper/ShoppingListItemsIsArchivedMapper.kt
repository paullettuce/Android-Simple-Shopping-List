package pl.paullettuce.simpleshoppinglist.domain.mapper

import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetailsWithItems

class ShoppingListItemsIsArchivedMapper :
    Mapper<ShoppingListDetailsWithItems, ShoppingListDetailsWithItems> {
    override fun map(input: ShoppingListDetailsWithItems): ShoppingListDetailsWithItems {
        input.items.forEach { it.isListActive = input.details.isActive }
        return input
    }
}