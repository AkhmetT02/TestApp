package com.example.appfortest.presenters

import com.example.appfortest.models.BookModel
import com.example.appfortest.providers.BooksMenuProvider
import com.example.appfortest.views.BooksMenuView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class BooksMenuPresenter : MvpPresenter<BooksMenuView>() {

    fun loadBooks() {
        viewState.startLoading()
        BooksMenuProvider(presenter = this).loadBooks()
    }

    fun finishLoadBooks(books: List<BookModel>) {
        viewState.endLoading()
        if (books.isEmpty()) {
            viewState.setupEmptyBooks()
        } else {
            viewState.setupBooks(books = books)
        }
    }

    fun showError(error: String) {
        viewState.showError(error = error)
    }
}