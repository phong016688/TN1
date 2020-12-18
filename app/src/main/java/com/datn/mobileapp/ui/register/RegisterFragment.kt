package com.datn.mobileapp.ui.register

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.FragmentLoginBinding
import com.datn.mobileapp.helpers.ValidatorHelper
import com.datn.mobileapp.utils.viewBindings
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.regex.Pattern

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val viewBinding by viewBindings(FragmentLoginBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register_button.setOnClickListener {
            validateEmail()
            validateConfirmPassWord()
            validatePassWord()
            validatePhone()
        }
    }

    private fun validatePhone() {
        val mPhone = phone_register_edit_text.text.toString().trim()
        if (mPhone.isEmpty()) {
            phone_register_text_input.error = "Please enter enough information"
        } else if (!Patterns.PHONE.matcher(mPhone).matches()) {
            phone_register_text_input.error = "Please reformat"
        } else {
            phone_register_text_input.error = null
        }
    }

    private fun validatePassWord() {
        val passwordInput = password_register_edit_text.text.toString().trim()
        if (passwordInput.isEmpty()) {
            password_register_text_input.error = "Please enter enough information"
        } else if (!ValidatorHelper.PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password_register_text_input.error =
                "Password must be in uppercase letters and numbers and "
        } else if (passwordInput.length in 16 downTo 7) {
            password_register_confirm_text_input.error = "Password greater than 8 -> 15characters "
        } else {
            password_register_text_input.error = null
        }
    }

    private fun validateConfirmPassWord() {
        val passConfirmInput = password_register_confirm_edit_text.text.toString().trim()
        if (passConfirmInput.isEmpty()) {
            password_register_confirm_text_input.error = "Please enter enough information"
        } else if (!ValidatorHelper.PASSWORD_PATTERN.matcher(passConfirmInput).matches()) {
            password_register_confirm_text_input.error =
                "Password must be in uppercase letters and numbers and "
        } else if (passConfirmInput.length in 16 downTo 7) {
            password_register_confirm_text_input.error = "Password greater than 8 -> 15characters "
        } else {
            password_register_confirm_text_input.error = null
        }
    }

    private fun validateEmail() {
        val emailInput = email_register_edit_text.text.toString().trim()
        if (emailInput.isEmpty()) {
            email_register_text_input.error = "Please enter enough information"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email_register_text_input.error = "Please reformat"
        } else {
            email_register_text_input.error = null
        }
    }
}
