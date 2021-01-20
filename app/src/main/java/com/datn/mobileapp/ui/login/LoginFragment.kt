package com.datn.mobileapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.TransitionInflater
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.FragmentLoginBinding
import com.datn.mobileapp.ui.MainActivity
import com.datn.mobileapp.ui.register.RegisterFragment
import com.datn.mobileapp.utils.viewBindings
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewBinding by viewBindings(FragmentLoginBinding::bind)
    private var callbackManager: CallbackManager? = null
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var signInClient: GoogleSignInClient? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onclick()
        //login gmail
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        signInClient = GoogleSignIn.getClient(requireContext(), gso)
        viewBinding.gmailImageButton.setOnClickListener {
            signIn()
        }

        //login facebook
        auth = Firebase.auth
        user = auth!!.currentUser
        callbackManager = CallbackManager.Factory.create();
        viewBinding.faceBookImageButton.setReadPermissions(listOf(EMAIL))
        viewBinding.faceBookImageButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    handleFacebookAccessToken(result!!.accessToken)
                    Log.d("AAAAAAAAAAAA", user?.displayName + "__" + user?.email + "__" + user?.uid)
                    //    updateUI()
                }

                override fun onCancel() {
                    Log.d(this::class.java.simpleName, "onCancel")
                }

                override fun onError(error: FacebookException?) {
                    Log.d(this::class.java.simpleName, error.toString())
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.shared_image)
        sharedElementReturnTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.shared_image)
    }

    private fun onclick() = viewBinding.apply {
        viewBinding.signUpTextView.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, RegisterFragment.newInstance())
                .addSharedElement(viewBinding.titleTextView, viewBinding.titleTextView.transitionName)
                .addSharedElement(viewBinding.emailTextInput, viewBinding.emailTextInput.transitionName)
                .addSharedElement(viewBinding.passwordTextInput, viewBinding.passwordTextInput.transitionName)
                .addSharedElement(viewBinding.loginButton, viewBinding.loginButton.transitionName)
            fragmentTransaction.commit()
        }
    }

    private fun signIn() {
        val signInIntent = signInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth!!.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(this::class.java.simpleName, "signInWithCredential:success")
                    val user = auth!!.currentUser
                    Log.d(
                        this::class.java.simpleName,
                        user?.phoneNumber + "___" + user?.displayName + "___" + user?.getEmail()
                    )
                    //  updateUI()
                } else {
                    Log.w(
                        this::class.java.simpleName,
                        "signInWithCredential:failure",
                        task.exception
                    )
                    // ...
                    Snackbar.make(requireView(), "Authentication Failed.", Snackbar.LENGTH_SHORT)
                        .show()
                    // updateUI()
                }
            }
    }

    private fun handleFacebookAccessToken(accessToken: AccessToken) {
        try {
            val credential = FacebookAuthProvider.getCredential(accessToken.token)
            auth!!.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Log.d(this::class.java.simpleName, "successful")
                        //   updateUI()
                    } else {
                        Log.d(
                            this::class.java.simpleName,
                            "signInWithCredential:failure",
                            task.exception
                        )
                        Toast.makeText(
                            requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } catch (e: Exception) {
        }
    }

    override fun onStart() {
        super.onStart()
//        if (auth!!.currentUser != null) {
//            updateUI()
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("ABB", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("ABB", "Google sign in failed", e)
                // ...
            }
        }
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
        const val RC_SIGN_IN = 2
        fun newInstance() = LoginFragment()
    }
}
