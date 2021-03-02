package com.example.appfortest.fragments.ui.book_menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
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
    private lateinit var booksViewModel: BookMenuViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyBooksTv: TextView
    private lateinit var loadingBar: ProgressBar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_books_menu, container, false)
        DaggerAppComponent.builder().appModule(AppModule(activity?.applicationContext!!)).fragmentModule(FragmentModule(fragment = this)).build().inject(this)

        booksViewModel = ViewModelProvider(activity as ViewModelStoreOwner).get(BookMenuViewModel::class.java)

        recyclerView = view.findViewById(R.id.recycler_books_menu)
        emptyBooksTv = view.findViewById(R.id.empty_books_menu_tv)
        loadingBar = view.findViewById(R.id.loading_bar_menu)

        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        recyclerView.adapter = adapter

        adapter.setOnBookClickListener(object: BooksAdapter.OnBookClickListener {
            override fun onBookClick(position: Int) {
                Log.e("TAG", "onBookClick: CLICKED")
                val bundle = Bundle()
                bundle.putParcelable("book", adapter.books[position])
                findNavController().navigate(R.id.action_booksMenuFragment_to_bookInfoFragment, bundle)
            }
        })


        booksViewModel.getBooks.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                presenter.loadBooks()
                Log.e("TAG", "onCreateView: ${it.size}")
            } else {
                setupBooksWithoutInternet(it)
                Log.e("TAG", "onCreateViewElse: ${it.size}")
            }
            Log.e("TAG", "onCreateViewQQQ: ")
        })

        if (booksViewModel.getBooks.value == null) {
            presenter.loadBooks()
        }
        return view
    }

    private fun setupBooksWithoutInternet(books: List<BookModel>) {
        adapter.setupBooks(books = books)
    }

    override fun setupBooks(books: List<BookModel>) {
        recyclerView.visibility = View.VISIBLE
        emptyBooksTv.visibility = View.INVISIBLE
        adapter.setupBooks(books = books)
        booksViewModel.addBooks(books = books)
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