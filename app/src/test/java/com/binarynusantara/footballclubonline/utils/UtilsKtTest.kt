package com.binarynusantara.footballclubonline.utils

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class UtilsKtTest {

    @Test
    fun testdateFormatToString() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse("2018-10-14")
        assertEquals("Sun, 14 Oct 2018", dateFormatToString(date))
    }
}