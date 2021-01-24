package pl.paullettuce.simpleshoppinglist.domain.mapper

import pl.paullettuce.simpleshoppinglist.domain.formatters.TimeFormatter
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListEntity

class ShoppingListEntityToDetailsMapper : Mapper<ShoppingListEntity, ShoppingListDetails> {
    override fun map(input: ShoppingListEntity): ShoppingListDetails {
        return ShoppingListDetails(
            input.id,
            input.name,
            TimeFormatter.friendlyFromMillis(input.creationTimestamp),
            input.isActive
        )
    }
}

class ShoppingListEntityToDetailsListMapper(
    private val itemMapper: ShoppingListEntityToDetailsMapper = ShoppingListEntityToDetailsMapper()
) : ListMapper<ShoppingListEntity, ShoppingListDetails> {
    override fun map(input: List<ShoppingListEntity>): List<ShoppingListDetails> {
        return input.map { itemMapper.map(it) }
    }
}