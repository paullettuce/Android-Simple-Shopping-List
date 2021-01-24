package pl.paullettuce.simpleshoppinglist.presentation.shopping_lists

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_shopping_list_details.view.*
import kotlinx.android.synthetic.main.list_item_shopping_list_details.view.name
import kotlinx.android.synthetic.main.list_item_shopping_list_details.view.subtitle
import pl.paullettuce.SwipeLayout
import pl.paullettuce.simpleshoppinglist.R
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.presentation.diff_callbacks.ShoppingListDetailsDiffCallback
import pl.paullettuce.simpleshoppinglist.presentation.extensions.inflate

class ShoppingListsAdapter(
    private val interaction: ShoppingListsContract.ListInteraction,
    diffCallback: ShoppingListDetailsDiffCallback
) : ListAdapter<ShoppingListDetails, ShoppingListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        return ShoppingListViewHolder(
            parent.inflate(R.layout.list_item_shopping_list_details),
            interaction
        )
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ShoppingListViewHolder(
    itemView: View,
    private val interaction: ShoppingListsContract.ListInteraction
) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ShoppingListDetails) {
        val subtitle = itemView.context.getString(R.string.creation_date, item.creationDate)
        itemView.name.text = item.name
        itemView.subtitle.text = subtitle
        itemView.mainView.setOnClickListener { interaction.onClick(item) }
        itemView.swipeLayout.reset()
        itemView.swipeLayout.blockSwipes(!item.isActive)
        itemView.swipeLayout.swipeListener = object : SwipeLayout.SwipeListener {
            override fun swipedToLeft() { }
            override fun swipedToRight() {
                interaction.archive(item)
            }
        }
    }
}