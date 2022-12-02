package com.example.bookstore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookstore.model.Book
import com.example.bookstore.model.BookListener
import com.example.bookstore.model.BookService

class BookViewModel(val bookService: BookService) : ViewModel() {
    var _ReadBook = MutableLiveData<BookListener>()
    var readBook = _ReadBook as LiveData<BookListener>

    fun getBook(){
        bookService.readBook(){
            if(it.status){
                _ReadBook.value = it
            }
        }
    }
}