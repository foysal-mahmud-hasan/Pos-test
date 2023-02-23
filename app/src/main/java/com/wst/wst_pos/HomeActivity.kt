package com.wst.wst_pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        drawerLayout = binding.drawerLayout
        navigationView = binding.navView
        toolbar = binding.toolbar

        setSupportActionBar(toolbar)

        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // Add the two image buttons to the right side of the toolbar
        val rightButtonsLayout = binding.toolbarRightButtons
        val barcode = ImageButton(this).apply {
            setImageResource(R.drawable.barcode)
            setOnClickListener { // add your code here }
            }
            val cart = ImageButton(this).apply {
                setImageResource(R.drawable.cart)
                setOnClickListener { // add your code here }
                }
                rightButtonsLayout.addView(barcode)
                rightButtonsLayout.addView(cart)

                navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
                    if (nd.id == nc.graph.getStartDestination()) {
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    } else {
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    }
                }
                NavigationUI.setupWithNavController(binding.navView, navController)
                navigationView.setNavigationItemSelectedListener { item: MenuItem ->
                    // your code
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    drawerLayout.close()
                    return@setNavigationItemSelectedListener true
                }
            }

            override fun onSupportNavigateUp(): Boolean {
                val navController = this.findNavController(R.id.nav_host_fragment)
                return NavigationUI.navigateUp(navController, drawerLayout)
            }
        }
    }
}