package com.example.appfortest.views

import com.example.appfortest.models.BookModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface BooksMenuView : MvpView {
    fun setupBooks(books: List<BookModel>)
    fun setupEmptyBooks()
    fun showError(error: String)
    fun startLoading()
    fun endLoading()
}