package com.example.appfortest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.appfortest.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class BooksFragment : Fragment() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private var backStackCount = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_books, container, false)

        bottomNavigationView = activity?.findViewById(R.id.bottom_navigation_main)!!

        bottomNavigationView.menu.clear()
        bottomNavigationView.inflateMenu(R.menu.menu)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            Log.i("TAG", "Clicked")
            when (it.itemId) {
                R.id.bottom_books -> childFragmentManager.beginTransaction().replace(R.id.books_fragment_container, BooksMenuFragment()).commit()
                R.id.bottom_downloaded -> childFragmentManager.beginTransaction().replace(R.id.books_fragment_container, DownloadedFragment()).commit()
                R.id.bottom_favourite -> childFragmentManager.beginTransaction().replace(R.id.books_fragment_container, FavouriteFragment()).commit()
            }

            return@setOnNavigationItemSelectedListener true
        }

        childFragmentManager.beginTransaction().replace(R.id.books_fragment_container, BooksMenuFragment()).commit()


        childFragmentManager.addOnBackStackChangedListener {

            if (childFragmentManager.backStackEntryCount > backStackCount) {
                parentFragmentManager.beginTransaction().addToBackStack("null").commit()
                backStackCount++
            } else {
                backStackCount--
            }

        }
        parentFragmentManager.addOnBackStackChangedListener {
            if (parentFragmentManager.backStackEntryCount < backStackCount) {
                childFragmentManager.popBackStack()
            }
        }
    }
}