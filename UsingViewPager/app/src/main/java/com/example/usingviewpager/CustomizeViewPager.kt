package com.example.usingviewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import java.util.*

class CustomizeViewPager(context: Context, attributeSet: AttributeSet) : ViewPager(context,attributeSet) {
    private var swipeEnabled = false

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return when(swipeEnabled) {
            true -> super.onTouchEvent(ev)
            false -> false
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return when(swipeEnabled){
            true -> super.onInterceptTouchEvent(ev)
            false -> false
        }
    }

    fun setSwipePagingEnabled(swipeEnable : Boolean) {
        this.swipeEnabled = swipeEnable
    }

}