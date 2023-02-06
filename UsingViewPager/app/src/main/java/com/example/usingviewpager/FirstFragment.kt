package com.example.usingviewpager

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.MotionEventCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.usingviewpager.databinding.FragmentFirstBinding
import java.util.*
import kotlin.collections.ArrayList

class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    lateinit var viewPager: CustomizeViewPager
    private lateinit var firstPagerAdapter: FirstPagerAdapter
    lateinit var listOfItem: ArrayList<DataContainer>
    private lateinit var dotOne: ImageView
    private lateinit var dotTwo: ImageView
    private lateinit var dotThree: ImageView
    private lateinit var button: Button

    // private lateinit var dotsLayout: LinearLayout
    var dotscount: Int = 0
    var current_position: Int = 0
    private var videoCount = 0
    private var swipeCount = 0
    private var autocycle = false
    private var touchListener: TouchListener? = null
    private var swipeTimer: Long = 5000

    private lateinit var handler: Handler


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)
        listOfItem = ArrayList<DataContainer>()
        //dotsLayout = binding.dotsIndicator
        //dots = ArrayList<ImageView>()
        //viewPager = binding.viewPager

        listOfItem.add(
            DataContainer(
                "android.resource://" + requireContext().packageName + "/" + R.raw.mycabs,
                "hello doing programming"
            )
        )
        listOfItem.add(
            DataContainer(
                "android.resource://" + requireContext().packageName + "/" + R.raw.mychat,
                "hey every One"
            )
        )
        listOfItem.add(
            DataContainer(
                "android.resource://" + requireContext().packageName + "/" + R.raw.mytv,
                "hello doing programming"
            )
        )

        Log.d("value", "dotCount - $dotscount")
        Log.d("value", "dotCount - $current_position")

        //createSlideShow(5000)
        //setImageList(listOfItem)
        init()

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, swipeTimer)
                when (position % 3) {
                    0 -> {
                        dotOne.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY)
                        dotTwo.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY)
                        dotThree.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY)
                    }
                    1 -> {
                        dotOne.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY)
                        dotTwo.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY)
                        dotThree.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY)
                    }
                    2 -> {
                        dotOne.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY)
                        dotTwo.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY)
                        dotThree.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        if (touchListener != null) {
            viewPager!!.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> touchListener!!.onTouched(ActionTypes.MOVE)
                    MotionEvent.ACTION_DOWN -> touchListener!!.onTouched(ActionTypes.DOWN)
                    MotionEvent.ACTION_UP -> touchListener!!.onTouched(ActionTypes.UP)
                }
                false
            }
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)
    }

    private var runnable = Runnable {
        viewPager.currentItem = viewPager.currentItem + 1
    }

    private fun init() {
        viewPager = binding.viewPager
        handler = Handler(Looper.myLooper()!!)
        firstPagerAdapter = FirstPagerAdapter(requireContext(), listOfItem, viewPager)
        viewPager.adapter = firstPagerAdapter
        dotOne = binding.dotOne
        dotTwo = binding.dotTwo
        dotThree = binding.dotThree
        button = binding.button
        dotOne.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY)
        viewPager.setSwipePagingEnabled(true) // true means scrollable auto and manual both

        button.setOnClickListener {
            if(swipeCount == 0){
                Toast.makeText(requireContext(),"$swipeCount",Toast.LENGTH_SHORT).show()
                viewPager.setSwipePagingEnabled(false)
                swipeTimer = 50000
                swipeCount = 1
            }else{
                Toast.makeText(requireContext(),"$swipeCount",Toast.LENGTH_SHORT).show()
                viewPager.setSwipePagingEnabled(true)
                swipeTimer = 50000
                swipeCount = 0
            }
        }

    }


    private fun createSlideShow(changeablePeriod: Long) {
        val interval_time: Long = changeablePeriod
        val handler = Handler()
        val runnable: Runnable = object : Runnable {
            override fun run() {
                if (current_position < listOfItem.size) {
                    viewPager.setCurrentItem(current_position++, true)
                    handler.postDelayed(this, interval_time)
                    if (current_position == listOfItem.size) {
                        current_position = 0
                    }
                }
            }

        }
        handler.postDelayed(runnable, interval_time)
    }

    //    fun setImageList(videoList : ArrayList<DataContainer>){
//        firstPagerAdapter = FirstPagerAdapter(requireContext(),videoList)
//        viewPager!!.adapter = firstPagerAdapter
//        videoCount = videoList.size
//        if(videoList.isNotEmpty()){
//            if(autocycle){
//                Log.d("values","autocycler - $autocycle")
//            }
//        }
//    }
}