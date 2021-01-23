package pl.paullettuce.simpleshoppinglist.domain.formatters

import java.text.SimpleDateFormat
import java.util.*

const val TIME_FORMAT = "MM/dd"
object TimeFormatter {

    fun friendlyFromMillis(datetime: Long): String {
        return format(datetime)
    }

    private fun format(datetime: Long): String {
        val formatter = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        val date = Date()
        date.time = datetime
        return formatter.format(date)
    }
}