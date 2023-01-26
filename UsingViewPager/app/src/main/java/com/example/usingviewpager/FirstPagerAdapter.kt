package com.example.usingviewpager

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import androidx.viewpager.widget.PagerAdapter
import java.util.*
import kotlin.collections.ArrayList

class FirstPagerAdapter(var context: Context,var itemList : ArrayList<DataContainer>) : PagerAdapter() {

    override fun getCount(): Int {
       return itemList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view == `object` as LinearLayout)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val item_view  = layoutInflater.inflate(R.layout.pager_layout,container,false)
        val holder = itemList[position]
        val videoView : VideoView = item_view.findViewById(R.id.videoHolder)
        videoView.setVideoPath(Uri.parse(holder.videoUri).toString())
        videoView.requestFocus()
        videoView.setOnPreparedListener {
            videoView.start()
            it.isLooping = true
        }
        val txtView : TextView = item_view.findViewById(R.id.text_holder)
        txtView.text = holder.surge_des
        container.addView(item_view)
        return item_view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}