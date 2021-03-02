package com.example.appfortest.fragments.ui.book_menu

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appfortest.models.BookModel

class BookMenuViewModel : ViewModel() {

    private val viewModelBooks = MutableLiveData<List<BookModel>>()

    fun addBooks(books: List<BookModel>) {
        viewModelBooks.value = books
    }
    val getBooks = viewModelBooks
}