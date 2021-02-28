package com.example.appfortest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appfortest.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SendFragment : Fragment() {

    private val TAG = "SettingsFragment"

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_send, container, false)

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
                R.id.bottom_books -> Log.e(TAG, "onViewCreated: BOTTOM_BOOKS")
                R.id.bottom_downloaded -> Log.e(TAG, "onViewCreated: BOTTOM_D")
                R.id.bottom_favourite -> Log.e(TAG, "onViewCreated: BOTTOM_F")
            }

            return@setOnNavigationItemSelectedListener true
        }
    }
}