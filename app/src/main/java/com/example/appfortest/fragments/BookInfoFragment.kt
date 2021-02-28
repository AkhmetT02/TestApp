package com.example.appfortest.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.appfortest.R
import com.example.appfortest.database.BooksDatabaseViewModel
import com.example.appfortest.models.BookModel
import com.squareup.picasso.Picasso

class BookInfoFragment(private val book: BookModel) : Fragment() {

    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var addToFavouriteBtn: Button
    private lateinit var downloadBtn: Button

    private lateinit var viewModel: BooksDatabaseViewModel
    private var isFavourite: Boolean = false
    private var exist: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_book_info, container, false)

        image = view.findViewById(R.id.info_image)
        title = view.findViewById(R.id.info_title)
        description = view.findViewById(R.id.info_description)
        addToFavouriteBtn = view.findViewById(R.id.add_to_favourite_btn)
        downloadBtn = view.findViewById(R.id.download_btn)

        viewModel = ViewModelProvider(this).get(BooksDatabaseViewModel::class.java)

        Picasso.get().load(book.imageLinks?.thumbnail).into(image)
        title.text = book.title
        description.text = book.description

        viewModel.allBooks.observe(viewLifecycleOwner, Observer {
            it.forEach {
                if (it.title == book.title) {
                    if (it.favourite) {
                        addToFavouriteBtn.text = "Remove from favourite"
                        isFavourite = true
                    } else {
                        addToFavouriteBtn.text = "Add to favourite"
                        isFavourite = false
                    }
                    if (it.downloaded) {
                        downloadBtn.text = "Downloaded"
                        downloadBtn.isEnabled = false
                    }
                    exist = true
                }
            }
        })

        addToFavouriteBtn.setOnClickListener {
            if (exist) {
                if (isFavourite) {
                    viewModel.updateFavouriteBook(false, book.title)
                } else {
                    viewModel.updateFavouriteBook(true, book.title)
                }
            } else {
                book.favourite = true
                viewModel.insertBook(book)
            }

        }
        downloadBtn.setOnClickListener {
            if (exist) {
                viewModel.updateDownloadedBook(true, book.title)
            } else {
                book.downloaded = true
                viewModel.insertBook(book)
            }
        }

        return view
    }
}