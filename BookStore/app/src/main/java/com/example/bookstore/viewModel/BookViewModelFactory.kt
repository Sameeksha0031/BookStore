package com.example.bookstore.viewModel

import android.widget.ViewSwitcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookstore.model.BookService

class BookViewModelFactory(private val bookService: BookService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookViewModel(bookService) as T
    }
}