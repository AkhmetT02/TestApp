package com.example.appfortest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appfortest.R
import com.example.appfortest.adapters.BooksAdapter
import com.example.appfortest.di.DaggerAppComponent
import com.example.appfortest.di.modules.AppModule
import com.example.appfortest.di.modules.FragmentModule
import com.example.appfortest.models.BookModel
import com.example.appfortest.presenters.BooksMenuPresenter
import com.example.appfortest.views.BooksMenuView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import javax.inject.Inject

class BooksMenuFragment : MvpAppCompatFragment(), BooksMenuView {

    @Inject lateinit var adapter: BooksAdapter
    @InjectPresenter lateinit var presenter: BooksMenuPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyBooksTv: TextView
    private lateinit var loadingBar: ProgressBar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_books_menu, container, false)
        DaggerAppComponent.builder().appModule(AppModule(activity?.applicationContext!!)).fragmentModule(FragmentModule(fragment = this)).build().inject(this)


        recyclerView = view.findViewById(R.id.recycler_books_menu)
        emptyBooksTv = view.findViewById(R.id.empty_books_menu_tv)
        loadingBar = view.findViewById(R.id.loading_bar_menu)

        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        recyclerView.adapter = adapter

        adapter.setOnBookClickListener(object: BooksAdapter.OnBookClickListener {
            override fun onBookClick(position: Int) {
                Log.e("TAG", "onBookClick: CLICKED")
                val fragment = BookInfoFragment(adapter.books[position])
                parentFragmentManager.beginTransaction()
                    .replace(R.id.books_fragment_container, fragment)
                    .addToBackStack("null")
                    .commit()
            }
        })

        presenter.loadBooks()
        return view
    }

    override fun setupBooks(books: List<BookModel>) {
        recyclerView.visibility = View.VISIBLE
        emptyBooksTv.visibility = View.INVISIBLE
        adapter.setupBooks(books = books)
    }

    override fun setupEmptyBooks() {
        recyclerView.visibility = View.INVISIBLE
        emptyBooksTv.visibility = View.VISIBLE
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun startLoading() {
        recyclerView.visibility = View.INVISIBLE
        emptyBooksTv.visibility = View.INVISIBLE
        loadingBar.visibility = View.VISIBLE
    }

    override fun endLoading() {
        loadingBar.visibility = View.INVISIBLE
    }
}