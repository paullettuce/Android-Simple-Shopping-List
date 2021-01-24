package pl.paullettuce.simpleshoppinglist

import android.app.Activity
import android.view.LayoutInflater
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import pl.paullettuce.simpleshoppinglist.presentation.dialogs.MinMaxEditText
import kotlin.math.min

@RunWith(RobolectricTestRunner::class)
class MinMaxEditTextTest {

    private lateinit var activityController: ActivityController<Activity>
    private lateinit var activity: Activity

    private lateinit var minMaxEditText: MinMaxEditText

    @Before
    fun setUp() {
        activityController = Robolectric.buildActivity(Activity::class.java)
        activity = activityController.get()

        minMaxEditText = MinMaxEditText(activity)
    }

    @Test
    fun `should not cap value if range is not set`() {
        minMaxEditText.setValue(100000)
        assertTextEqualTo("100000")
    }

    @Test
    fun `should cap min value if range is set`() {
        minMaxEditText.setValueRange(1, 100)
        minMaxEditText.setValue(-1000)
        assertTextEqualTo("1")
    }

    @Test
    fun `should cap max value if range is set`() {
        minMaxEditText.setValueRange(1, 100)
        minMaxEditText.setValue(100000)
        assertTextEqualTo("100")
    }

    @Test
    fun `should reset to min value if trying to delete last digit with range set`() {
        minMaxEditText.setValueRange(1, 100)
        minMaxEditText.setText("")
        assertTextEqualTo("1")
    }

    private fun assertTextEqualTo(expected: String) {
        assertEquals(expected, minMaxEditText.text.toString())
    }
}