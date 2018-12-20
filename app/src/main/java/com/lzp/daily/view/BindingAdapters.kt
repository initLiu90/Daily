package com.lzp.daily.view

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.lzp.daily.model.bean.HistoryMessage
import com.lzp.daily.view.home.HomeAdapter

object HomeBindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    fun items(recyclerView: RecyclerView, stories: List<HistoryMessage.Stories>) {
        val adapter = recyclerView.adapter as? HomeAdapter
        adapter?.addStories(stories)
    }

    @BindingAdapter("showLoadMore")
    @JvmStatic
    fun setShowLoadMore(recyclerView: RecyclerView, show: Boolean) {
        val adapter = recyclerView.adapter
        if (adapter is HomeAdapter) {
            if (show) adapter.showLoadMore = true else adapter.showLoadMore = false
        }
    }

    @InverseBindingAdapter(attribute = "showLoadMore")
    @JvmStatic
    fun getShowLoadMore(recyclerView: RecyclerView): Boolean {
        return true
    }

    @BindingAdapter("showLoadMoreAttrChanged")
    @JvmStatic
    fun setLoadMoreListener(recyclerView: RecyclerView, listener: InverseBindingListener) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                recyclerView ?: return
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(1)) {
                        listener.onChange()
                    }
                }
            }
        })
    }
}