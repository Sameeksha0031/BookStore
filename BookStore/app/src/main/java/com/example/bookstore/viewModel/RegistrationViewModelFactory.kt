package com.example.bookstore.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookstore.model.UserListener
import com.example.bookstore.model.UserService

class RegistrationViewModelFactory(private val userService: UserService) : ViewModelProvider.Factory
{ override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(userService) as T
    }
}