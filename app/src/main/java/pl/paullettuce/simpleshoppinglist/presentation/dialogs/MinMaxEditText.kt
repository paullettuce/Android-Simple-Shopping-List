package pl.paullettuce.simpleshoppinglist.presentation.dialogs

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet


class MinMaxEditText : androidx.appcompat.widget.AppCompatEditText {
    private val DEFAULT_MIN = 0
    private val DEFAULT_MAX = Int.MAX_VALUE

    init {
        inputType = InputType.TYPE_CLASS_NUMBER
    }

    interface OnChange {
        fun onValueChange(number: Int)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val minMaxCappingTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            try {
                val number = s.toString().toInt()
                when {
                    number > max -> replaceAll(s, max)
                    number < min -> replaceAll(s, min)
                    else -> notifyIfChanged(number)
                }
            } catch (e: NumberFormatException) {
                replaceAll(s, min)
            }
        }

        private fun replaceAll(s: Editable?, replacement: Int) {
            val endIndex = s?.length ?: 0
            s?.replace(0, endIndex, replacement.toString())
            notifyIfChanged(replacement)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private var oldValue: Int = 0

    var min: Int = DEFAULT_MIN
        private set(value) {
            field = value
            capValueIfLowerThanMin()
        }

    var max: Int = DEFAULT_MAX
        private set(value) {
            field = value
            capValueIfHigherThanMax()
        }
    var onChangeCallback: OnChange? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setValueRange(min, max)
    }

    fun setValueRange(min: Int, max: Int) {
        this.min = min
        this.max = max

        removeTextChangedListener(minMaxCappingTextWatcher)
        addTextChangedListener(minMaxCappingTextWatcher)
    }

    fun setValue(number: Int) {
        setText(number.toString())
    }

    @Throws(NumberFormatException::class)
    fun getValue(): Int = parseTextField()

    private fun capValueIfLowerThanMin() {
        tryParsingTextField { number ->
            if (number < min) {
                setNumber(min)
            }
        }
    }

    private fun capValueIfHigherThanMax() {
        tryParsingTextField { number ->
            if (number > max) {
                setNumber(max)
            }
        }
    }

    private fun tryParsingTextField(action: (number: Int) -> Unit) {
        try {
            val currentNumber = parseTextField()
            action(currentNumber)
        } catch (e: NumberFormatException) {
            insertMinIfEmpty()
        }
    }

    @Throws(NumberFormatException::class)
    private fun parseTextField() = text.toString().toInt()

    private fun insertMinIfEmpty() {
        if (text.isNullOrEmpty())
            setNumber(min)
    }

    private fun setNumber(number: Int) {
        setText(number.toString())
        notifyIfChanged(number)
    }

    private fun notifyIfChanged(newValue: Int) {
        if (newValue != oldValue) {
            oldValue = newValue
            onChangeCallback?.onValueChange(newValue)
        }
    }

}