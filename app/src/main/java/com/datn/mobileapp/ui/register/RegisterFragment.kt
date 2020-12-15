package com.datn.mobileapp.ui.register

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.FragmentLoginBinding
import com.datn.mobileapp.utils.viewBindings
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val viewBinding by viewBindings(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun validateEmail() {
        val fullnameInput = full_name_register_edit_text.text.toString().trim()
        val emailInput = email_register_edit_text.text.toString().trim()
        val phoneInput = phone_register_edit_text.text.toString()
        val passInput = password_register_edit_text.text.toString().trim()
        val passConfirmInput = password_register_edit_text.text.toString().trim()
        if (emailInput.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches() ||
            fullnameInput.isNotEmpty() && Patterns.DOMAIN_NAME.matcher(fullnameInput).matches() ||
            passInput.isNotEmpty() && passInput.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")) ||
            phoneInput.isNotEmpty() && Patterns.PHONE.matcher(phoneInput).matches() ||
            passConfirmInput.isNotEmpty() && passConfirmInput.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"))
        ) {

        }
    }
}
