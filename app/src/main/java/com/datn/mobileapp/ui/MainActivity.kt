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
import com.phong.lib.LoadingDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewBinding by viewBindings(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = viewBinding.navView

        fragment_container.post {
            val navController = Navigation.findNavController(this, R.id.fragment_container)
            navView.setupWithNavController(navController)
        }
        viewBinding.abc.setOnClickListener {
            Log.d("#####", "abc abc")
        }
        viewBinding.loadingButton.setOnClickListener {
            Log.d("###", "sadasd")
        }
    }

    override fun onResume() {
        super.onResume()
        LoadingDialog().apply {
            show(supportFragmentManager, LoadingDialog.TAG)
            viewBinding.container.postDelayed(Runnable {
                this.dismiss()
            }, 2000)
        }
    }
}