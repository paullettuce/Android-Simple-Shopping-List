package pl.paullettuce.simpleshoppinglist.presentation.shopping_list_details

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_shopping_list_item.view.*
import pl.paullettuce.simpleshoppinglist.R
import pl.paullettuce.simpleshoppinglist.presentation.diff_callbacks.ShoppingListItemDiffCallback
import pl.paullettuce.simpleshoppinglist.presentation.extensions.inflate
import pl.paullettuce.simpleshoppinglist.presentation.extensions.showStrikeThrough
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

class ShoppingListItemsAdapter(
    private val interaction: ShoppingListDetailsContract.ListInteraction,
    diffCallback: ShoppingListItemDiffCallback
) : ListAdapter<ShoppingListItemEntity, ShoppingListItemViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemViewHolder {
        return ShoppingListItemViewHolder(
            parent.inflate(R.layout.list_item_shopping_list_item),
            interaction
        )
    }

    override fun onBindViewHolder(holder: ShoppingListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ShoppingListItemViewHolder(
    itemView: View,
    private val interaction: ShoppingListDetailsContract.ListInteraction
) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ShoppingListItemEntity) {
        val quantity = "x${item.quantity}"
        itemView.name.text = item.name
        itemView.subtitle.text = quantity
        itemView.name.showStrikeThrough(item.isDone)
        itemView.subtitle.showStrikeThrough(item.isDone)
        itemView.doneIcon.setDoneNotDoneIcon(item.isDone)
        if (item.isListArchived) {
            itemView.doneIcon.setOnClickListener(null)
        } else {
            itemView.doneIcon.setOnClickListener {
                if (item.isDone) {
                    interaction.unmarkAsDone(item)
                } else {
                    interaction.markAsDone(item)
                }
            }
        }
    }

    private fun ImageView.setDoneNotDoneIcon(done: Boolean) {
        if (done) setImageResource(R.drawable.ic_done)
        else setImageResource(R.drawable.ic_blank_check_box)
    }
}