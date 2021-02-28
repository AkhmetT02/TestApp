package com.example.appfortest.providers

import com.example.appfortest.di.App
import com.example.appfortest.models.BookModel
import com.example.appfortest.models.Result
import com.example.appfortest.network.RetrofitService
import com.example.appfortest.presenters.BooksMenuPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BooksMenuProvider(private val presenter: BooksMenuPresenter) {

    @Inject lateinit var mService: RetrofitService

    fun loadBooks() {
        App().getAppComponent().inject(this)
        mService.getBooks(query = "dante", apiKey = "AIzaSyDXv_Q77u8jbPmVZIPILQN4OCPLSNLGX0M").enqueue(object: Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                if (t.message != null) {
                    presenter.showError(t.message!!)
                }
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful && response.body() != null) {
                    val books = ArrayList<BookModel>()
                    response.body()?.items?.forEach {
                        books.add(it.volumeInfo)
                    }
                    presenter.finishLoadBooks(books = books)
                } else {
                    presenter.showError(response.message())
                }
            }
        })
    }
}