package com.example.bookstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bookstore.R
import com.example.bookstore.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replacingFragment(LoginFragment())
    }

    fun replacingFragment(fragment: Fragment){
      supportFragmentManager.beginTransaction().replace(R.id.fragment_activity_container,fragment)
            .commit()
    }
}