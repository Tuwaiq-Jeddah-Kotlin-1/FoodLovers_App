package com.example.foodloverscapston2.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.foodloverscapston2.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var sher = this.getSharedPreferences("share_pref", MODE_PRIVATE)
        var lang = sher.getString("share", "")
       lang.let{
        setLocale(lang.toString())
       }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavView = findViewById<BottomNavigationView>(R.id.BottomNavMenu)
        bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.LoginFragment -> bottomNavView.visibility = View.GONE
                R.id.detailsFragment -> bottomNavView.visibility = View.GONE
                R.id.forgotPassWordFragment -> bottomNavView.visibility = View.GONE
                else -> bottomNavView.visibility = View.VISIBLE
            }
        }
    }
    fun setLocale(lang: String){
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        this.resources.updateConfiguration(config,this.resources.displayMetrics)

        var sharedPreferences = this.getSharedPreferences("share_pref", MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString("share_pref", lang)
        editor.commit()
    }
}




