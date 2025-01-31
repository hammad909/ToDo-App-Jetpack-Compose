package com.example.todo.todo.presentation.components.component

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun FormatToReadableDate(date : Long): String{
    val sdf = SimpleDateFormat("dd MM, h:mm a, yyy", Locale.ENGLISH)
    sdf.timeZone = TimeZone.getTimeZone("Asia/Karachi")
    return sdf.format(Date(date))
}