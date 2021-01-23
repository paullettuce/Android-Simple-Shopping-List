package pl.paullettuce.simpleshoppinglist.presentation.shopping_lists

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_shopping_lists.*
import pl.paullettuce.simpleshoppinglist.R
import pl.paullettuce.simpleshoppinglist.presentation.dialogs.SubmitNameDialog
import pl.paullettuce.simpleshoppinglist.presentation.extensions.showView
import pl.paullettuce.simpleshoppinglist.presentation.lists.RecyclerViewMargin
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingListsFragment : Fragment(R.layout.fragment_shopping_lists) {

    @Inject
    lateinit var presenter: ShoppingListsContract.Presenter

    @Inject
    lateinit var shoppingListsAdapter: ShoppingListsAdapter

    @Inject
    lateinit var submitNameDialog: SubmitNameDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reactToPassedArguments(arguments)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setupRecView()
        setListeners()
    }

    private fun observeData() {
        presenter.shoppingLists.observe(viewLifecycleOwner, Observer {
            shoppingListsAdapter.submitList(it)
            showNoItemsInfo(it.isEmpty())
        })
    }

    private fun showNoItemsInfo(empty: Boolean) {
        if (empty) {
            Toast.makeText(context, "noitems", Toast.LENGTH_SHORT).show()
        } else {
//            hide
        }
    }

    private fun showSubmitNameDialog() {
        submitNameDialog.titleText = getString(R.string.enter_shopping_list_name)
        submitNameDialog.setSubmitCallback { name -> presenter.createShoppingList(name) }
        submitNameDialog.show(childFragmentManager, "enter_shopping_list_name")
    }

    private fun setListeners() {
        accessActivityFAB()?.setOnClickListener { showSubmitNameDialog() }
    }

    private fun setupRecView() {
        shoppingListsRecView.layoutManager = LinearLayoutManager(this.context)
        shoppingListsRecView.adapter = shoppingListsAdapter
        shoppingListsRecView.addItemDecoration(
            RecyclerViewMargin(verticalMarginDp = R.dimen.recycler_view_item_margin)
        )
        shoppingListsAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                shoppingListsRecView.scrollToPosition(positionStart)
            }
        })
        shoppingListsRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    accessActivityFAB()?.hide()
                } else {
                    accessActivityFAB()?.show()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun accessActivityFAB(): FloatingActionButton? = activity?.findViewById(R.id.addFAB)

    private fun reactToPassedArguments(args: Bundle?) {
        val fetchActive = shouldFetchActive(args)
        presenter.setFetchActiveLists(fetchActive)
    }

    private fun shouldFetchActive(args: Bundle?) =
        args?.getBoolean(SHOULD_FETCH_ACTIVE_BUNDLE_KEY)
            ?: throw RuntimeException("No value passed for parameter shouldFetchActive")

    companion object {
        private val SHOULD_FETCH_ACTIVE_BUNDLE_KEY = "SHOULD_FETCH_ACTIVE_BUNDLE_KEY"

        fun getActiveShoppingListsFragment(): ShoppingListsFragment {
            return shoppingListsFragment(active = true)
        }

        fun getArchivedShoppingListsFragment(): ShoppingListsFragment {
            return shoppingListsFragment(active = false)
        }

        private fun shoppingListsFragment(active: Boolean): ShoppingListsFragment {
            val fragment = ShoppingListsFragment()
            val args = Bundle()
            args.putBoolean(SHOULD_FETCH_ACTIVE_BUNDLE_KEY, active)
            fragment.arguments = args
            return fragment
        }
    }
}