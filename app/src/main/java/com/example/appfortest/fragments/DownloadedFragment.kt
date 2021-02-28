package com.example.appfortest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appfortest.R
import com.example.appfortest.adapters.BooksAdapter
import com.example.appfortest.database.BooksDatabaseViewModel
import com.example.appfortest.di.DaggerAppComponent
import com.example.appfortest.di.modules.AppModule
import com.example.appfortest.di.modules.FragmentModule
import javax.inject.Inject

class DownloadedFragment : Fragment() {

    @Inject lateinit var adapter: BooksAdapter

    private lateinit var recyclerBooks: RecyclerView
    private lateinit var emptyBooksTv: TextView

    private lateinit var viewModel: BooksDatabaseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_downloaded, container, false)

        DaggerAppComponent.builder().appModule(AppModule(activity?.applicationContext!!)).fragmentModule(FragmentModule(fragment = this)).build().inject(this)

        viewModel = ViewModelProvider(this).get(BooksDatabaseViewModel::class.java)

        recyclerBooks = view.findViewById(R.id.recycler_downloaded_books)
        emptyBooksTv = view.findViewById(R.id.empty_books_downloaded_tv)

        recyclerBooks.layoutManager = LinearLayoutManager(context)
        recyclerBooks.adapter = adapter

        viewModel.downloadedBooks.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                emptyBooksTv.visibility = View.VISIBLE
            } else {
                emptyBooksTv.visibility = View.INVISIBLE
            }
            adapter.setupBooks(it)
        })


        return view
    }
}