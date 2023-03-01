package com.wst.wst_pos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.wst.wst_pos.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        drawerLayout = binding.drawerLayout
        navigationView = binding.navView

        // Set up navigation controller
        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        // Set up nav drawer
//        navigationView.setNavigationItemSelectedListener { item: MenuItem ->
//            // handle menu item clicks
//            when (item.itemId) {
//                R.id.menu_item1 -> {
//                    // handle menu item 1
//                    true
//                }
//                R.id.menu_item2 -> {
//                    // handle menu item 2
//                    true
//                }
//                else -> false
//            }
//        }

        // Set up image buttons
//        binding.imgBtnBarcode.setOnClickListener {
//            // handle image button 1 click
//        }
//        binding.imgBtnCart.setOnClickListener {
//            // handle image button 2 click
//        }

        // Lock the drawer on the start destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
