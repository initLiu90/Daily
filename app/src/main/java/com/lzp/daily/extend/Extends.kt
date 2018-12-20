package com.lzp.daily.extend

import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

/**
 * 系统类的扩展函数，都集中在此文件中
 */


/**
 * 获取当前日期
 */
fun Date.today(): String {
    var sdf: Format = SimpleDateFormat("yyyyMMdd")
    return sdf.format(this)
}

/**
 * 获取昨天的日期
 * @param today 可选参数，如果不传，就返回当前日期的上一天，如果传就返回传入参数的上一天
 */
fun Date.tomorrow(today: String? = null): String {
    val baseDay = today ?: today()
    val year = baseDay.substring(0, 4).toInt()
    val month = baseDay.substring(4, 6).toInt()
    val day = baseDay.substring(6, 8).toInt()
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month - 1)
    calendar.set(Calendar.DAY_OF_MONTH, day)

    var sdf: Format = SimpleDateFormat("yyyyMMdd")
    val t: Long = calendar.timeInMillis
    val l: Long = t - 24 * 3600 * 1000
    var d: Date = Date(l)
    return sdf.format(d)
}