package com.lzp.daily.model

import com.lzp.daily.model.bean.HistoryMessage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("/api/4/news/before/{date}")
    fun historyMessage(@Path("date") date: String): Observable<HistoryMessage>
}