package com.lzp.daily.model.bean

import java.lang.StringBuilder

class HistoryMessage {
    var date: String = ""
    var stories = emptyList<Stories>()

    class Stories {
        var title: String = ""
        var ga_prefix: String = ""
        var images = emptyList<String>()
        var type: Int = 0
        var id: Long = 0
        override fun toString(): String {
            return "title:${title},ga_prefix:${ga_prefix}"
        }
    }

    override fun toString(): String {
        val sb: StringBuilder = StringBuilder()
        sb.append("data:${date}")
        stories.map {
            sb.append(it.toString())
        }
        return sb.toString()
    }
}
