package com.lzp.daily.view.home

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lzp.daily.databinding.RecyclerItemBinding
import com.lzp.daily.databinding.RecyclerLoadmoreItemBinding
import com.lzp.daily.model.bean.HistoryMessage

const val ITEM_TYPE_NORMAL = 0
const val ITEM_TYPE_LOADMORE = 1

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val stories = arrayListOf<HistoryMessage.Stories>()
    var showLoadMore = false
        set(value) {
            if (value != field) {
                field = value
//                Log.e("Test", "showloadmore=" + field)
                if (field) notifyItemInserted(this.stories.size) else notifyItemRemoved(this.stories.size)
            }
        }

    fun addStories(stories: List<HistoryMessage.Stories>) {
        this.stories.addAll(stories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_NORMAL -> {
                val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HomeViewHolder(binding)
            }
            ITEM_TYPE_LOADMORE -> {
                val binding = RecyclerLoadmoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HomeViewLoadMoreHolder(binding)
            }
            else -> throw IllegalStateException("invalide viewType:${viewType}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < this.stories.size) ITEM_TYPE_NORMAL else ITEM_TYPE_LOADMORE
    }

    override fun getItemCount(): Int {
        return if (showLoadMore) this.stories.size + 1 else this.stories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE_NORMAL -> {
                (holder as HomeViewHolder).binding.itemStory.text = stories[position].title
            }
        }

    }

    class HomeViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)
    class HomeViewLoadMoreHolder(val binding: RecyclerLoadmoreItemBinding) : RecyclerView.ViewHolder(binding.root)
}