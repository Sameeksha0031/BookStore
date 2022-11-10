package com.example.bookstore.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookstore.model.UserService

class LogInViewModelFactory(private val userAuthServices: UserService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LogInViewModel(userAuthServices) as T
    }
}