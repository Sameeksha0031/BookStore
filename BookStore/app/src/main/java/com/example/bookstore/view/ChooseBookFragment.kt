package com.example.bookstore.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentChooseBookBinding
import com.example.bookstore.model.Book
import com.example.bookstore.model.BookService
import com.example.bookstore.viewModel.BookViewModel

class ChooseBookFragment : Fragment() {
    lateinit var binding : FragmentChooseBookBinding
    lateinit var recyclerView: RecyclerView
    lateinit var bookViewModel: BookViewModel
    lateinit var bookList : ArrayList<Book>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChooseBookBinding.bind(view)
        recyclerView = RecyclerView(requireContext())
        bookViewModel = BookViewModel(BookService())
        bookList = ArrayList<Book>()

        setHasOptionsMenu(true)

        recyclerView = binding.bookRecyclerView
        recyclerView.setHasFixedSize(true)
        //recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = GridLayoutManager(requireActivity(),2)
        Log.d("Displaying Fragment","inside the Display Fragment")
        displayBook()
    }

    fun displayBook(){
        bookViewModel.getBook()
        bookViewModel.readBook.observe(viewLifecycleOwner , Observer {
            if(it.status){
                for(book in it.bookArayList){
                    bookList.add(book)
                }
                recyclerView.adapter = BookViewAdapter(requireContext(),bookList)
                Toast.makeText(context,it.msg,Toast.LENGTH_SHORT).show()
            }
        })
    }
}