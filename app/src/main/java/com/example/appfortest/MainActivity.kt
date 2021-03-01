package com.example.appfortest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.DownloadListener
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.appfortest.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var bottomNavigation: BottomNavigationView

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        bottomNavigation = findViewById(R.id.bottom_navigation_main)

        navController = findNavController(R.id.main_nav_host)
        bottomNavigation.setupWithNavController(navController)


        appBarConfiguration = AppBarConfiguration(setOf(R.id.booksMenuFragment,
                R.id.downloadedFragment,
                R.id.favouriteFragment,
                R.id.settingsFragment,
                R.id.sendFragment), drawer)


        //When fragment changed
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.settingsFragment -> hideBottomNavigation()
                R.id.sendFragment -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }

        //Setup UI with navigationView, navController and appBar
        NavigationUI.setupWithNavController(navigationView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }

    private fun hideBottomNavigation() {
        bottomNavigation.visibility = View.INVISIBLE
    }

    private fun showBottomNavigation() {
        bottomNavigation.visibility = View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}