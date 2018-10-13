package com.binarynusantara.footballclubonline.utils

import android.content.Context
import com.binarynusantara.footballclubonline.data.db.MyDatabaseOpenHelper

val Context.db: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)