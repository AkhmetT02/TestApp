package com.example.appfortest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appfortest.models.BookModel

@Database(entities = [BookModel::class], version = 5, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {

    companion object {
        private var database: BooksDatabase? = null
        private val DB_NAME = "books.db"

        fun getDatabase(context: Context): BooksDatabase {
            if (database == null) {
                database = Room.databaseBuilder(context, BooksDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database!!
        }
    }

    abstract fun getBooksDao(): BooksDao
}