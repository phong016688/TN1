package com.datn.mobileapp.ui.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.TransitionInflater
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.FragmentRegisterBinding
import com.datn.mobileapp.helpers.ValidatorHelper
import com.datn.mobileapp.ui.login.LoginFragment
import com.datn.mobileapp.utils.viewBindings

@Suppress("DEPRECATION")
class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val viewBinding by viewBindings(FragmentRegisterBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.registerButton.setOnClickListener {
            validateEmail()
            validateConfirmPassWord()
            validatePassWord()
            validatePhone()
        }
        clickSignIn()
    }

    private fun clickSignIn() {
        viewBinding.signInTextView.setOnClickListener {
            val fragmentTransaction: FragmentTransaction =
                requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, LoginFragment.newInstance())
                .addSharedElement(
                    viewBinding.titleRegisterTextView,
                    viewBinding.titleRegisterTextView.transitionName
                )
                .addSharedElement(
                    viewBinding.emailRegisterTextInput,
                    viewBinding.emailRegisterTextInput.transitionName
                )
                .addSharedElement(
                    viewBinding.passwordRegisterTextInput,
                    viewBinding.passwordRegisterTextInput.transitionName
                )
                .addSharedElement(
                    viewBinding.registerButton,
                    viewBinding.registerButton.transitionName
                )
            fragmentTransaction.commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.shared_image)
        sharedElementReturnTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.shared_image)
    }

    private fun validatePhone() = viewBinding.apply {
        val mPhone = phoneRegisterEditText.text.toString().trim()
        if (mPhone.isEmpty()) {
            phoneRegisterTextInput.error = "Please enter enough information"
        } else if (!Patterns.PHONE.matcher(mPhone).matches()) {
            phoneRegisterTextInput.error = "Please reformat"
        } else {
            phoneRegisterTextInput.error = null
        }
    }

    private fun validatePassWord() = viewBinding.apply {
        val passwordInput = passwordRegisterEditText.text.toString().trim()
        if (passwordInput.isEmpty()) {
            passwordRegisterTextInput.error = "Please enter enough information"
        } else if (!ValidatorHelper.PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            passwordRegisterTextInput.error =
                "Password must be in uppercase letters and numbers and "
        } else if (passwordInput.length in 16 downTo 7) {
            passwordRegisterConfirmTextInput.error = "Password greater than 8 -> 15characters "
        } else {
            passwordRegisterConfirmTextInput.error = null
        }
    }

    private fun validateConfirmPassWord() = viewBinding.apply {
        val passConfirmInput = passwordRegisterConfirmEditText.text.toString().trim()
        if (passConfirmInput.isEmpty()) {
            passwordRegisterConfirmTextInput.error = "Please enter enough information"
        } else if (!ValidatorHelper.PASSWORD_PATTERN.matcher(passConfirmInput).matches()) {
            passwordRegisterConfirmTextInput.error =
                "Password must be in uppercase letters and numbers and "
        } else if (passConfirmInput.length in 16 downTo 7) {
            passwordRegisterConfirmTextInput.error = "Password greater than 8 -> 15characters "
        } else {
            passwordRegisterConfirmTextInput.error = null
        }
    }

    private fun validateEmail() = viewBinding.apply {
        val emailInput = emailRegisterEditText.text.toString().trim()
        if (emailInput.isEmpty()) {
            emailRegisterTextInput.error = "Please enter enough information"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailRegisterTextInput.error = "Please reformat"
        } else {
            emailRegisterTextInput.error = null
        }
    }

    companion object {
        fun newInstance() = RegisterFragment()
    }
}
