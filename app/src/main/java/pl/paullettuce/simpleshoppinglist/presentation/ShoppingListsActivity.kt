package pl.paullettuce.simpleshoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import pl.paullettuce.simpleshoppinglist.R
import pl.paullettuce.simpleshoppinglist.presentation.shopping_lists.ShoppingListsFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.apply {
            val transaction = beginTransaction()
            transaction.replace(R.id.tempcontaint, activeListsFragment)
            transaction.commit()
        }
    }
}