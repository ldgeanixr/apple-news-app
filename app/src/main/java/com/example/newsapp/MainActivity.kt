package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var showMenuPrev = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
                as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val showMenu = when(destination.id) {
                R.id.bookmarksFragment -> false
                else -> true
            }
            if (showMenuPrev == null) {
                showMenuPrev = showMenu
                invalidateOptionsMenu()
            }else if (showMenu != showMenuPrev) {
                showMenuPrev = showMenu
                invalidateOptionsMenu()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (showMenuPrev) menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemBookmarks -> {
                navController.navigate(NavGraphDirections.toBookmarksFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}