package com.example.bookstore.model

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class BookService {
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var firestore: FirebaseFirestore

    init{
        getinit()
    }

    fun getinit() {
        firebaseStorage = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()
    }

    fun readBook(listener: (BookListener) -> Unit){
      var bookList = ArrayList<Book>()
      firestore.collection("book")
          .get()
          .addOnSuccessListener {
              if(it != null){
                  for(doc in it.documents){
                      var bookId : String = doc["bookId"].toString()
                      var author : String = doc["author"].toString()
                      var bookImage : String = doc["bookImage"].toString()
                      var bookTitle : String = doc["bookTitle"].toString()
                      var price : String = doc["price"].toString()

                      var book = Book(bookId = bookId, author = author, bookImage = bookImage,
                          bookTitle = bookTitle, price = price.toDouble())
                      bookList.add(book)
                   }
                  Log.d("BookServices","$bookList")
              }
              listener(BookListener(bookList,true,"Success...."))
          }
    }
}