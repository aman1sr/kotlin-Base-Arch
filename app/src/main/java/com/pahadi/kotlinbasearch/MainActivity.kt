package com.pahadi.kotlinbasearch

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.pahadi.kotlinbasearch.databinding.ActivityMainBinding
import com.pahadi.kotlinbasearch.ui.auth.AuthViewModel
import io.realworld.api.models.entities.User


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        setSupportActionBar(binding.appBarMain.toolbar)


//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        /*
        *  inside --> AppBarConfiguration --> we set the ID wrt nav_menu & navigation_main_Editor ( we put all 3 common ids )
        *            add the menu_id here, to make the Frg as top level destination (ie, will show drawable icon in TOP LEFT corner)
        * */
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_galleryy , R.id.nav_auth , R.id.nav_setting , R.id.nav_feed, R.id.nav_my_feed
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        
        authViewModel.user.observe({lifecycle}){
            updateMenu(it)
            Toast.makeText(this, "Welcome ${it?.username}", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateMenu(user: User?) {
        when (user) {
            is User -> {
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.main_menu_user)
            }
            else ->{
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.main_menu_guest)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}