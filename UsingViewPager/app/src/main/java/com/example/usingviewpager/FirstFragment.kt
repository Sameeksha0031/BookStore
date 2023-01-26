package com.example.usingviewpager

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.example.usingviewpager.databinding.FragmentFirstBinding
import java.util.*
import kotlin.collections.ArrayList

class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    lateinit var viewPager: ViewPager
    private lateinit var firstPagerAdapter: FirstPagerAdapter
    lateinit var listOfItem: ArrayList<DataContainer>
    private lateinit var dotsLayout: LinearLayout
    var dotscount: Int = 0
    var current_position: Int = 0
    private var videoCount = 0
    private var period : Long = 0
    private var autocycle = false
    private var swipeTimer = Timer()


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
        dotsLayout = binding.dotsIndicator
        //dots = ArrayList<ImageView>()
        viewPager = binding.viewPager

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

        Log.d("value","dotCount - $dotscount")
        Log.d("value","dotCount - $current_position")

        createSlideShow(5000)
        setImageList(listOfItem)

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


    fun setImageList(videoList : ArrayList<DataContainer>){
        firstPagerAdapter = FirstPagerAdapter(requireContext(),videoList)
        viewPager!!.adapter = firstPagerAdapter
        videoCount = videoList.size
        if(videoList.isNotEmpty()){
            if(autocycle){
                Log.d("values","autocycler - $autocycle")
            }
        }
    }
}