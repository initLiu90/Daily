package com.lzp.daily.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

private const val DEFAULT_DIVIDER_HEIGHT = 10

class HomeItemDecoration : RecyclerView.ItemDecoration() {
    private val mPaint: Paint

    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.RED
            style = Paint.Style.FILL
        }

    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        parent ?: return
        if (parent.getChildAdapterPosition(view) > 0) {
            outRect?.top = DEFAULT_DIVIDER_HEIGHT
        }
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        parent ?: return
        val left = parent.paddingLeft
        val right = parent.right - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val top = child.top - DEFAULT_DIVIDER_HEIGHT
            val bottom = child.top
            c?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
        }
    }
}