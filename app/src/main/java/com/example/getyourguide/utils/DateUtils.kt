package com.example.getyourguide.utils

import com.google.gson.internal.bind.util.ISO8601Utils
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {

    var toDateFormatter = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH)

    fun convertDate(date: String): String{
        val createdAt = ISO8601Utils.parse(date, ParsePosition(0))
        return toDateFormatter.format(createdAt)
    }

}