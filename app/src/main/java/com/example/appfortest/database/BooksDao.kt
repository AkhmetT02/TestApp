package com.example.appfortest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appfortest.models.BookModel

@Dao
interface BooksDao {

    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<BookModel>>

    @Query("SELECT * FROM books WHERE downloaded=1")
    fun getAllDownloadedBooks(): LiveData<List<BookModel>>

    @Query("SELECT * FROM books WHERE favourite=1")
    fun getAllFavouriteBooks(): LiveData<List<BookModel>>

    @Query("SELECT * FROM books WHERE title=:title")
    fun getBookByTitle(title: String): BookModel

    @Query("DELETE FROM books WHERE title=:title")
    fun deleteBookByTitle(title: String)

    @Insert
    fun insertBook(book: BookModel)

    @Query("UPDATE books SET favourite=:favourite WHERE title=:title")
    fun updateFavouriteBook(favourite: Boolean, title: String)

    @Query("UPDATE books SET downloaded=:downloaded WHERE title=:title")
    fun updateDownloadedBook(downloaded: Boolean, title: String)
}