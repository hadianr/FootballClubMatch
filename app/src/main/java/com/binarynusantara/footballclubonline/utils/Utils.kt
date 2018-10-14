package com.binarynusantara.footballclubonline.utils

import android.annotation.SuppressLint
import android.content.Context
import com.binarynusantara.footballclubonline.data.db.MyDatabaseOpenHelper
import java.text.SimpleDateFormat
import java.util.*

val Context.db: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)

@SuppressLint("SimpleDateFormat")
fun dateFormatToString(date: Date?) : String? = with(date ?: Date()){
    SimpleDateFormat("EEE, dd MMM yyy").format(this)
}