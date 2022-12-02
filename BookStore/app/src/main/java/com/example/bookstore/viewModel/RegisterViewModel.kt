package com.example.bookstore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookstore.model.User
import com.example.bookstore.model.UserListener
import com.example.bookstore.model.UserService

class RegisterViewModel(val userService: UserService) : ViewModel() {
    var _RegisterUser = MutableLiveData<UserListener>()
    var registerUser = _RegisterUser as LiveData<UserListener>

    fun putUserInFirestore(user: User?){
        userService.userRegistration(user!!){
            _RegisterUser.value = it
            }
        }
}