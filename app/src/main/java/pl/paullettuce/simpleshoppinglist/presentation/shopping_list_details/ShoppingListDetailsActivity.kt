package pl.paullettuce.simpleshoppinglist.presentation.shopping_list_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_details.*
import pl.paullettuce.simpleshoppinglist.R
import pl.paullettuce.simpleshoppinglist.presentation.lists.RecyclerViewMargin
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingListDetailsActivity : AppCompatActivity(), ShoppingListDetailsContract.View,
    ShoppingListDetailsContract.ListInteraction {

    @Inject
    lateinit var presenter: ShoppingListDetailsContract.Presenter

    @Inject
    lateinit var listItemsAdapter: ShoppingListItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_details)
        reactToPassedArguments(intent)
        setupRecView()
        setupListeners()
        observeData()
    }

    override fun markAsDone(listItemEntity: ShoppingListItemEntity) {
        presenter.markAsDone(listItemEntity)
    }

    override fun unmarkAsDone(listItemEntity: ShoppingListItemEntity) {
        presenter.unmarkAsDone(listItemEntity)
    }

    private fun observeData() {
        presenter.shoppingListDetailsLiveData.observe(this, Observer {
            listItemsAdapter.submitList(it.items)
        })
    }

    private fun setupListeners() {
        addFAB.setOnClickListener {
            presenter.addShoppingListItem("supername", 10)
        }
        backArrowButton.setOnClickListener {
            finish()
        }
    }

    private fun setupRecView() {
        listItemsRecView.layoutManager = LinearLayoutManager(this)
        listItemsRecView.adapter = listItemsAdapter
        listItemsRecView.addItemDecoration(
            RecyclerViewMargin(verticalMarginDp = R.dimen.recycler_view_item_margin)
        )
        listItemsRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    addFAB.hide()
                } else {
                    addFAB.show()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun reactToPassedArguments(intent: Intent) {
        val shoppingListId: Long = retrieveShoppingListId(intent)
        presenter.setShoppingListId(shoppingListId)
    }

    private fun retrieveShoppingListId(intent: Intent) =
        (intent.extras?.getLong(SHOPPING_LIST_ID_KEY)
            ?: throw RuntimeException("No value passed for parameter shouldFetchActive"))

    companion object {
        private const val SHOPPING_LIST_ID_KEY = "SHOPPING_LIST_ID_KEY"

        fun launchIntent(
            context: Context?,
            shoppingListId: Long
        ): Intent {
            val intent = Intent(context, ShoppingListDetailsActivity::class.java)
            val args = Bundle()
            args.putLong(SHOPPING_LIST_ID_KEY, shoppingListId)
            intent.putExtras(args)
            return intent
        }
    }
}