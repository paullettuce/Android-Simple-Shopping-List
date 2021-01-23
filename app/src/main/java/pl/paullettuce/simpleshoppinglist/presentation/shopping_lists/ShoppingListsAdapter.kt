package pl.paullettuce.simpleshoppinglist.presentation.shopping_lists

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetailsDiffCallback

class ShoppingListsAdapter(
    diffCallback: ShoppingListDetailsDiffCallback
) : ListAdapter<ShoppingListDetails, ShoppingListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class ShoppingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}