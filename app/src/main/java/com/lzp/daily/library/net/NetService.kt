package com.lzp.daily.library.net

import retrofit2.Retrofit
import retrofit2.adapter.convert.ConvertCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetService private constructor(val retrofit: Retrofit) {

    companion object {
        val instance: NetService

        init {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://news-at.zhihu.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addCallAdapterFactory(ConvertCallAdapterFactory.create())
                    .build()
            instance = NetService(retrofit)
        }
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}