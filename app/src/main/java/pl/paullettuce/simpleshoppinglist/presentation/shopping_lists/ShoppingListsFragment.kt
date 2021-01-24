package pl.paullettuce.simpleshoppinglist.presentation.shopping_lists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_details.*
import kotlinx.android.synthetic.main.fragment_shopping_lists.*
import pl.paullettuce.simpleshoppinglist.R
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.presentation.dialogs.NameDialog
import pl.paullettuce.simpleshoppinglist.presentation.lists.RecyclerViewMargin
import pl.paullettuce.simpleshoppinglist.presentation.shopping_list_details.ShoppingListDetailsActivity
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingListsFragment : Fragment(R.layout.fragment_shopping_lists),
    ShoppingListsContract.ListInteraction {

    @Inject
    lateinit var presenter: ShoppingListsContract.Presenter

    @Inject
    lateinit var shoppingListsAdapter: ShoppingListsAdapter

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

    override fun archive(item: ShoppingListDetails) {
        presenter.archiveList(item)
    }

    override fun onClick(item: ShoppingListDetails) {
        openDetailsActivity(item)
    }

    private fun observeData() {
        presenter.shoppingLists.observe(viewLifecycleOwner, Observer {
            shoppingListsAdapter.submitList(it)
            showNoItemsInfo(it.isEmpty())
        })
    }

    private fun showNoItemsInfo(empty: Boolean) {
        if (empty) {
//            show
        } else {
//            hide
        }
    }

    private fun openDetailsActivity(item: ShoppingListDetails) {
        val launchIntent = ShoppingListDetailsActivity.launchIntent(context, item.id)
        startActivity(launchIntent)
    }

    private fun showSubmitNameDialog() {
        NameDialog().apply {
            setSubmitCallback { name -> presenter.createShoppingList(name) }
            show(this@ShoppingListsFragment.childFragmentManager, "NameDialog")
        }
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
        shoppingListsAdapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                shoppingListsRecView.scrollToPosition(positionStart)
            }
        })
        shoppingListsRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                showFAB(dy <= 0)
            }
        })
    }

    private fun showFAB(show: Boolean) {
        if (show) {
            accessActivityFAB()?.show()
        } else {
            accessActivityFAB()?.hide()
        }
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
        private const val SHOULD_FETCH_ACTIVE_BUNDLE_KEY = "SHOULD_FETCH_ACTIVE_BUNDLE_KEY"

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