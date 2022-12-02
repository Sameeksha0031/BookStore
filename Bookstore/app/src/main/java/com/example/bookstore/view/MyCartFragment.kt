package com.example.bookstore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookstore.R

class MyCartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_cart, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val expandedFragment = view.findViewById<ExpandableLayout>(R.id.expandable)
//        var shrink = 0
//         expandedFragment.setOnClickListener {
//             if(shrink == 0){
//                 shrink = 1
//                 expandedFragment.expand()
//             }else{
//                 shrink = 0
//                 expandedFragment.collapse()
//             }
//         }
//    }
}