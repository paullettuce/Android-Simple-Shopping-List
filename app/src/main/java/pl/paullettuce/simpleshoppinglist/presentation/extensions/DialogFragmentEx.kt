package pl.paullettuce.simpleshoppinglist.presentation.extensions

import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

fun DialogFragment.setMatchParentWidth() {
    dialog?.window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}