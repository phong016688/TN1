package com.datn.mobileapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.ActivityMainBinding
import com.datn.mobileapp.utils.viewBindings
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewBinding by viewBindings(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeLanguage(Locale.US)
        val navView: BottomNavigationView = viewBinding.navView
        fragment_container.post {
            val navController = Navigation.findNavController(this, R.id.fragment_container)
            navView.setupWithNavController(navController)
        }
    }

    @Suppress("DEPRECATION")
    private fun changeLanguage(locale: Locale) {
        if (resources.configuration.locale.displayLanguage == locale.displayLanguage) return
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        recreate()
    }
}