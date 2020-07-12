package com.example.getyourguide

import com.example.getyourguide.utils.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class DateUtilTest {

    val DATE_TO_PARSE = "2020-03-12T14:20:22+01:00"
    val DATE_AFTER_PARSE = "Thursday, March 12, 2020"

    @Test
    fun testDateConversion(){
        val result = DateUtils.convertDate(DATE_TO_PARSE)
        assertEquals(result, DATE_AFTER_PARSE)
    }
}