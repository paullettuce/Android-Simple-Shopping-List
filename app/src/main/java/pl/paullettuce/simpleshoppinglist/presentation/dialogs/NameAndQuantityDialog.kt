package pl.paullettuce.simpleshoppinglist.presentation.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_name_and_quantity.*
import kotlinx.android.synthetic.main.dialog_submit_text.cancelButton
import kotlinx.android.synthetic.main.dialog_submit_text.editText
import kotlinx.android.synthetic.main.dialog_submit_text.submitButton
import pl.paullettuce.simpleshoppinglist.R
import pl.paullettuce.simpleshoppinglist.presentation.extensions.setMatchParentWidth

interface NameAndQuantityDialogCallback {
    fun submit(name: String, quantity: Int)
}

class NameAndQuantityDialog : DialogFragment() {

    private var callback: NameAndQuantityDialogCallback? = null

    override fun onStart() {
        super.onStart()
        setMatchParentWidth()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_name_and_quantity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancelButton.setOnClickListener { dismissAllowingStateLoss() }
        submitButton.setOnClickListener { onSubmit() }

//        quantityEditText.setValueRange(1, 999)
        increaseButton.setOnClickListener { quantityEditText.setValue(quantityEditText.getValue() + 1) }
        decreaseButton.setOnClickListener { quantityEditText.setValue(quantityEditText.getValue() - 1) }

        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onSubmit()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        editText.text.clear()
        quantityEditText.text?.clear()
        super.onDismiss(dialog)
    }

    fun setCallback(func: (String, Int) -> Unit) {
        callback = object : NameAndQuantityDialogCallback {
            override fun submit(name: String, quantity: Int) = func(name, quantity)
        }
    }

    private fun onSubmit() {
        val name = getName()
        val quantity = getQuantity()
        if (name.isNotBlank()) {
            dismissAllowingStateLoss()
            callback?.submit(name, quantity)
        }
    }

    private fun getName(): String {
        return editText.text.toString()
    }

    private fun getQuantity() = quantityEditText.getValue()
}