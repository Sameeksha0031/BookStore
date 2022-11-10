package com.example.bookstore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.model.User
import com.example.bookstore.model.UserListener
import com.example.bookstore.model.UserService

class LogInViewModel(val userService: UserService) {
    var _UserLogin = MutableLiveData<UserListener>()
    var _ForgotPassword = MutableLiveData<UserListener>()
    val userLogin = _UserLogin as LiveData<UserListener>
    val forgotPwd = _ForgotPassword as LiveData<UserListener>

    fun loginUser(user: User){
        userService.userLogin(user){
            if(it.status){
                _UserLogin.value = it
            }
        }
    }

    fun forgotPassword(user: User){
        userService.userForgotPassword(user){
            if(it.status){
                _ForgotPassword.value = it
            }
        }
    }
}