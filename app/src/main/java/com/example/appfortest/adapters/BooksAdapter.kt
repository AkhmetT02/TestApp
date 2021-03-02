package com.example.appfortest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.appfortest.R
import com.example.appfortest.fragments.ui.book_menu.BooksMenuFragment
import com.example.appfortest.fragments.ui.DownloadedFragment
import com.example.appfortest.models.BookModel
import com.squareup.picasso.Picasso

class BooksAdapter(private val fragment: Fragment, private val picasso: Picasso) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val books = ArrayList<BookModel>()
        get() = field

    private var onBookClickListener: OnBookClickListener? = null

    fun setOnBookClickListener(onBookClickListener: OnBookClickListener) {
        this.onBookClickListener = onBookClickListener
    }

    fun setupBooks(books: List<BookModel>) {
        this.books.addAll(books)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View? = null

        return if (fragment is BooksMenuFragment) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.book_poster_item, parent, false)
            BookPosterViewHolder(view)
        } else if (fragment is DownloadedFragment) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.downloaded_book_item, parent, false)
            DownloadedBookViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.favourite_book_item, parent, false)
            FavouriteBookViewHolder(view)
        }
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookPosterViewHolder) {
            holder.bind(book = books[position])
        } else if (holder is DownloadedBookViewHolder) {
            holder.bind(book = books[position])
        } else if (holder is FavouriteBookViewHolder) {
            holder.bind(book = books[position])
        }
    }

    inner class BookPosterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = itemView.findViewById(R.id.image_poster_item)
        private val genre: TextView = itemView.findViewById(R.id.genre_poster_item)
        private val name: TextView = itemView.findViewById(R.id.name_poster_item)

        init {
            itemView.setOnClickListener {
                onBookClickListener?.onBookClick(adapterPosition)
            }
        }

        fun bind(book: BookModel) {
            picasso.load(book.imageLinks?.thumbnail).into(image)
            name.text = book.title
            genre.text = book.categories.toString()
        }
    }
    inner class DownloadedBookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = itemView.findViewById(R.id.image_downloaded_item)
        private val name: TextView = itemView.findViewById(R.id.name_downloaded_item)
        private val description: TextView = itemView.findViewById(R.id.description_downloaded_item)

        init {
            itemView.setOnClickListener {
                onBookClickListener?.onBookClick(adapterPosition)
            }
        }

        fun bind(book: BookModel) {
            picasso.load(book.imageLinks?.thumbnail).into(image)
            name.text = book.title
            description.text = book.description
        }
    }
    inner class FavouriteBookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = itemView.findViewById(R.id.image_favourite_item)

        fun bind(book: BookModel) {
            picasso.load(book.imageLinks?.thumbnail).into(image)
        }
    }


    interface OnBookClickListener {
        fun onBookClick(position: Int)
    }
}