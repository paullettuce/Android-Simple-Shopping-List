package pl.paullettuce.simpleshoppinglist.presentation.shopping_lists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.tabLayout
import kotlinx.android.synthetic.main.content_main.viewPager
import pl.paullettuce.simpleshoppinglist.R
import pl.paullettuce.simpleshoppinglist.presentation.extensions.showView
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class ShoppingListsActivity : AppCompatActivity() {

    @Inject
    @Named("active")
    lateinit var activeListsFragment: ShoppingListsFragment

    @Inject
    @Named("archived")
    lateinit var archivedListsFragment: ShoppingListsFragment

    private val viewPagerAdapter =
        ViewPagerAdapter(
            this
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager()
    }

    private fun setupViewPager() {
        viewPagerAdapter.addFragment(activeListsFragment, 0)
        viewPagerAdapter.addFragment(archivedListsFragment, 1)
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                addFAB.showView(position == 0)
            }
        })
    }

    private fun getTabTitle(position: Int): String {
        return if (position == 0) {
            getString(R.string.active_lists)
        } else {
            getString(R.string.archived_lists)
        }
    }
}

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    private val fragments = mutableMapOf<Int, Fragment>()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]!!
    }

    fun addFragment(fragment: Fragment, position: Int) {
        fragments[position] = fragment
    }
}