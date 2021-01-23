package pl.paullettuce.simpleshoppinglist.presentation.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.showView() {
    isVisible = true
}

fun View.hideView() {
    isVisible = false
}

fun View.showView(show: Boolean) {
    isVisible = show
}