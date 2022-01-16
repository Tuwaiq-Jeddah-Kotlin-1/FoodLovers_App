package com.example.foodloverscapston2.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodloverscapston2.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var shared = this.getSharedPreferences("share_pref", MODE_PRIVATE)
        var lang = shared.getString("share", "")
        setLocale(lang.toString())

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavView = findViewById<BottomNavigationView>(R.id.BottomNavMenu)
        bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.LoginFragment -> {
                    bottomNavView.visibility = View.GONE
                    supportActionBar?.hide()
                }
                R.id.detailsFragment -> {
                    bottomNavView.visibility = View.GONE
                    supportActionBar?.hide()
                }
                R.id.forgotPassWordFragment -> {
                    bottomNavView.visibility = View.GONE
                    supportActionBar?.hide()
                }
                R.id.addRecipeDetailsFragment -> {
                    bottomNavView.visibility = View.GONE
                    supportActionBar?.hide()
                }

                R.id.explorerFragment -> {
                    bottomNavView.visibility = View.VISIBLE
                    supportActionBar?.hide()
                }
                R.id.myRecipeFragment -> {
                    bottomNavView.visibility = View.VISIBLE
                    supportActionBar?.hide()
                }
                R.id.ProfileFragment -> {
                    bottomNavView.visibility = View.VISIBLE
                    supportActionBar?.hide()
                }

                R.id.editFragment -> {
                    bottomNavView.visibility = View.GONE
                    supportActionBar?.show()
                }
                R.id.addFragment -> {
                    bottomNavView.visibility = View.GONE
                    supportActionBar?.show()
                }

            }
        }
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.explorerFragment, R.id.myRecipeFragment,
                R.id.ProfileFragment, R.id.addFragment, R.id.editFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        this.resources.updateConfiguration(config, this.resources.displayMetrics)

        var sharedPreferences = this.getSharedPreferences("share_pref", MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString("share_pref", lang)
        editor.apply()
    }
}




