package com.datn.mobileapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.datn.mobileapp.R

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val navFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        navFragment!!.childFragmentManager.fragments.forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
    }
}