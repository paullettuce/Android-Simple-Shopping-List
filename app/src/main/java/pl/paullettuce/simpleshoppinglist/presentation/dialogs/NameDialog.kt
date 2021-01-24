package pl.paullettuce.simpleshoppinglist.presentation.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_submit_text.*
import pl.paullettuce.simpleshoppinglist.R
import pl.paullettuce.simpleshoppinglist.presentation.extensions.setMatchParentWidth

interface NameDialogCallback {
    fun submit(name: String)
}

class NameDialog : DialogFragment() {
    private var nameDialogCallback: NameDialogCallback? = null

    override fun onStart() {
        super.onStart()
        setMatchParentWidth()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_submit_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancelButton.setOnClickListener { dismissAllowingStateLoss() }

        submitButton.setOnClickListener {
            onSubmit()
        }

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
        super.onDismiss(dialog)
    }

    fun setSubmitCallback(func: (String) -> Unit) {
        nameDialogCallback = object : NameDialogCallback {
            override fun submit(name: String) = func(name)
        }
    }

    private fun onSubmit() {
        val name = getName()
        if (name.isNotBlank()) {
            dismissAllowingStateLoss()
            nameDialogCallback?.submit(name)
        }
    }

    private fun getName(): String {
        return editText.text.toString()
    }
}