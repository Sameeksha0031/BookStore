package com.example.bookstore.view

import android.app.Activity
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.model.Book
import com.example.bookstore.model.BookListener

class BookViewAdapter(private val context: Context,private val bookList: ArrayList<Book>) :
    RecyclerView.Adapter<BookViewAdapter.BookViewHolder>() {

    var allbooks = mutableListOf<Book>().apply {
        addAll(bookList)
        notifyDataSetChanged()
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var bookImg : ImageView
        var bookAuthor  : TextView
        var bookPrice  : TextView
        var bookTitle :  TextView
        var add_to_bag_button : Button
        var added_to_bag_button : Button



        init {
            bookImg = itemView.findViewById(R.id.book_image_view)
            bookAuthor = itemView.findViewById(R.id.author)
            bookPrice = itemView.findViewById(R.id.price)
            bookTitle = itemView.findViewById(R.id.book_description)
            add_to_bag_button = itemView.findViewById(R.id.add_to_bag)
            added_to_bag_button = itemView.findViewById(R.id.added_to_bag)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
       val layerInflater = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_of_books,parent,false)
        return BookViewHolder(layerInflater)
    }

    override fun getItemCount(): Int {
        return allbooks.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book : Book = allbooks[position]
        var addBagbuttonVisibilty : Boolean = true
        holder.bookTitle.text = book.bookTitle
        holder.bookAuthor.text = book.author
        holder.bookPrice.text = book.price.toString()
        Glide.with(context)
            .load(book.bookImage)
            .into(holder.bookImg)

        holder.bookImg.setOnClickListener {
            val transaction = it.context as AppCompatActivity
            transaction.supportFragmentManager.beginTransaction()
                .replace(R.id.book_container,MyCartFragment())
                .addToBackStack(null)
                .commit()
        }

        holder.add_to_bag_button.setOnClickListener {
            holder.add_to_bag_button.visibility = View.GONE
            holder.added_to_bag_button.visibility =  View.VISIBLE
            val transaction = it.context as AppCompatActivity
            transaction.supportFragmentManager.beginTransaction()
                .replace(R.id.book_container,MyCartFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}