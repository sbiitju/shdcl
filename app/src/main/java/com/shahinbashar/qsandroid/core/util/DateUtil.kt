package com.shahinbashar.qsandroid.core.util

import java.text.SimpleDateFormat
import java.util.Calendar

import java.util.Date
import java.util.Locale


//val dateFormat = "2023-06-08T07:29:23.967Z"
val dateFormat = "yyyy-MM-dd'T'hh:mm:ss'Z'"

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

fun String.toDate(): Date? {
    val format = SimpleDateFormat(dateFormat, Locale.getDefault())
    return format.parse(this)
}

fun makeDateObject(day: Int, month: Int, year: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month-1, day)
    return calendar.time
}

fun Date?.toString(): String {
    val format = SimpleDateFormat(dateFormat, Locale.getDefault())
    return format.format(this)
}

fun Date?.toServerDateTime(): String {
    return this.toString()
}


fun Date?.toDisplayDate(): String {
    val format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return format.format(this)
}

fun Date?.toServerDate(): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'00:00:00'Z'", Locale.getDefault())
    return format.format(this)
}

fun Date?.toDisplayDateTime(): String {
    val format = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
    return format.format(this)
}


fun Date?.toDayInMonth(): Int {
    val format = SimpleDateFormat("dd", Locale.getDefault())
    return format.format(this).toInt()
}


object DateUtil {
    fun getCurrentMonth(): Int {
        val format = SimpleDateFormat("MM", Locale.getDefault())
        return format.format(Date()).toInt()
    }

    fun getCurrentYear(): Int {
        val format = SimpleDateFormat("yyyy", Locale.getDefault())
        return format.format(Date()).toInt()
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//fun getFormattedDateTime(day: Int, month: Int, year: Int, withoutTime: Boolean? = false): String {
//    val localDate = LocalDate.of(year, month, day)
//    val localDateTime = LocalDateTime.of(localDate, LocalDateTime.MIN.toLocalTime())
//    val zonedDateTime = localDateTime.atZone(ZoneOffset.UTC)
//    val formatter = DateTimeFormatter.ISO_INSTANT
//    val dateFormat = SimpleDateFormat("d MMMM, yyyy", Locale.ENGLISH)
//    val calendar = Calendar.getInstance()
//    calendar.set(year, month - 1, day)
//    return if (withoutTime == true) dateFormat.format(calendar.time)
//    else formatter.format(zonedDateTime)
//}