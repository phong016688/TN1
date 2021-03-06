package com.datn.mobileapp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.FragmentLoginBinding
import com.datn.mobileapp.utils.viewBindings
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewBinding by viewBindings(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
