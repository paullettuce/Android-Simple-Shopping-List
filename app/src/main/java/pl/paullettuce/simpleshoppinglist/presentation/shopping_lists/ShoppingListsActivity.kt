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
import pl.paullettuce.simpleshoppinglist.presentation.extensions.findCurrentFragment
import pl.paullettuce.simpleshoppinglist.presentation.extensions.showView
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class ShoppingListsActivity : AppCompatActivity() {

    private val viewPagerPageChangeCallback = ViewPagerPageChangeCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager()
        setListeners()
    }

    override fun onDestroy() {
        viewPager.unregisterOnPageChangeCallback(viewPagerPageChangeCallback)
        super.onDestroy()
    }

    private fun setupViewPager() {
        viewPager.adapter = ShoppingListsViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        viewPager.registerOnPageChangeCallback(viewPagerPageChangeCallback)
    }

    private fun setListeners() {
        addFAB.setOnClickListener {
            (viewPager.findCurrentFragment(supportFragmentManager) as? ShoppingListsContract.FABInteraction)?.onAddFABClick()
        }
    }

    private fun getTabTitle(position: Int): String {
        return if (position == 0) {
            getString(R.string.active_lists)
        } else {
            getString(R.string.archived_lists)
        }
    }

    inner class ViewPagerPageChangeCallback : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            addFAB.showView(position == 0)
        }
    }
}

class ShoppingListsViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            ShoppingListsFragment.getActiveShoppingListsFragment()
        } else {
            ShoppingListsFragment.getArchivedShoppingListsFragment()
        }
    }
}