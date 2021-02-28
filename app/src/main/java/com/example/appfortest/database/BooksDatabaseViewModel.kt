package com.example.appfortest.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.appfortest.models.BookModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksDatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val database = BooksDatabase.getDatabase(application)
    val downloadedBooks = database.getBooksDao().getAllDownloadedBooks()
    val favouriteBooks = database.getBooksDao().getAllFavouriteBooks()
    val allBooks = database.getBooksDao().getAllBooks()

    fun insertBook(book: BookModel) = CoroutineScope(Dispatchers.IO).launch {
        database.getBooksDao().insertBook(book = book)
    }
    suspend fun getBookByTitle(title: String): BookModel = withContext(Dispatchers.IO) {
        database.getBooksDao().getBookByTitle(title = title)
    }
    fun deleteBookByTitle(title: String) = CoroutineScope(Dispatchers.IO).launch {
        database.getBooksDao().deleteBookByTitle(title = title)
    }
    fun updateFavouriteBook(favourite: Boolean, title: String) = CoroutineScope(Dispatchers.IO).launch {
        database.getBooksDao().updateFavouriteBook(favourite, title)
    }
    fun updateDownloadedBook(downloaded: Boolean, title: String) = CoroutineScope(Dispatchers.IO).launch {
        database.getBooksDao().updateDownloadedBook(downloaded, title)
    }
}