package com.lzp.daily.util

import com.lzp.daily.extend.today
import com.lzp.daily.extend.tomorrow
import java.util.*

fun today(): String {
    return Date().today()
}

fun tommow(today: String? = null): String {
    return Date().tomorrow(today)
}