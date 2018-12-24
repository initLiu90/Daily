package com.lzp.daily.viewmodel.home

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableBoolean
import android.util.Log
import com.lzp.daily.BR
import com.lzp.daily.library.net.NetService
import com.lzp.daily.model.HomeService
import com.lzp.daily.model.bean.HistoryMessage
import com.lzp.daily.util.today
import com.lzp.daily.util.tommow
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewmodel : BaseObservable() {
    val isRefreshing: ObservableBoolean = ObservableBoolean(false)

    private var startDate: String = ""

    var lastStories = emptyList<HistoryMessage.Stories>()
        @Bindable
        get
        private set

    var stories = emptyList<HistoryMessage.Stories>()
        @Bindable
        get
        private set

    private var date = today()

    var loadMore: Boolean = false
        @Bindable
        get
        set(value) {
            if (value == field) return
            field = value
            if (field) {
                notifyPropertyChanged(BR.loadMore)
                loadHistoryMessage(tommow(date))
            }
        }

    fun start() {
        date = today()
        startDate = date
        loadHistoryMessage(date)
    }

    private fun loadHistoryMessage(date: String) {
        NetService.instance
                .create(HomeService::class.java)
                .historyMessage(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("Test", "message=" + it.toString())
                    this.date = date
                    loadMore = false
                    stories = it.stories
                    notifyPropertyChanged(BR.stories)
                    notifyPropertyChanged(BR.loadMore)
                }, {
                    Log.e("Test", "request error:" + it.message)
                })
    }

    fun refresh() {
        isRefreshing.set(true)
        if (today() == startDate) {
            isRefreshing.set(false)
            Log.e("Test", "alread lasted")
        } else {
            NetService.instance
                    .create(HomeService::class.java)
                    .historyMessage(date)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.e("Test", "message=" + it.toString())
                        startDate = it.date
                        lastStories = it.stories
                        notifyPropertyChanged(BR.lastStories)
                        isRefreshing.set(false)
                    }, {
                        Log.e("Test", "request error:" + it.message)
                    })
        }
    }
}