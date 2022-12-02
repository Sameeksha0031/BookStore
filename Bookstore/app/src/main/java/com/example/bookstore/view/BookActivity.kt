package com.example.bookstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bookstore.R

class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        replaceFragment(ChooseBookFragment())
    }

  fun replaceFragment(chooseBookFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransport = fragmentManager.beginTransaction()
        fragmentTransport.replace(R.id.book_container,chooseBookFragment).commit()
    }
}