package com.datn.mobileapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.FragmentLoginBinding
import com.datn.mobileapp.ui.MainActivity
import com.datn.mobileapp.utils.viewBindings
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.MainScope


@Suppress("DEPRECATION")
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewBinding by viewBindings(FragmentLoginBinding::bind)
    private var callbackManager: CallbackManager? = null
    private var auth: FirebaseAuth? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create();
        viewBinding.faceBookImageButton.setReadPermissions(listOf(EMAIL))
        viewBinding.faceBookImageButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    handleFacebookAccessToken(result!!.accessToken)
                    Log.d("AAD", result?.accessToken.toString())
                    updateUI()
                }

                override fun onCancel() {
                    Log.d("AAA", "CCCC")
                }

                override fun onError(error: FacebookException?) {
                    Log.d("AAA", error.toString())
                }
            })
    }

    private fun handleFacebookAccessToken(accessToken: AccessToken) {
        try {
            val credential = FacebookAuthProvider.getCredential(accessToken.token)
            auth!!.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        updateUI()
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(
                            requireContext(), "Authentication succes.",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("AAB", "signInWithCredential:success")
                        //  updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("AAC", "signInWithCredential:failure", task.exception)
                        Toast.makeText(
                            requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //  updateUI()
                    }
                    // ...
                }
        } catch (e: Exception) {

        }

    }

    override fun onStart() {
        super.onStart()
        if (auth!!.currentUser != null) {
            updateUI()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun updateUI() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

    private fun checkLogin() {

        viewBinding.faceBookImageButton.setOnClickListener {

        }
    }

    companion object {
        const val EMAIL = "email"
    }
}
