package com.datn.mobileapp.data.repository

import com.datn.mobileapp.data.cache.AppSharedPreferences
import com.datn.mobileapp.data.cache.AppPreferencesKey
import com.datn.mobileapp.data.remote.error.AppError
import com.datn.mobileapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val sharedPreferences: AppSharedPreferences
) : AuthRepository {
    override suspend fun loginFirebase(userName: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(userName, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val token = it.result?.user?.getIdToken(true) ?: ""
                    sharedPreferences.put(AppPreferencesKey.USER_ID_TOKEN, token)
                } else {
                    throw AppError.FirebaseLoginError("", "", it.exception?.message.toString())
                }
            }
            .addOnFailureListener {
                sharedPreferences.clearKey(AppPreferencesKey.USER_ID_TOKEN)
                throw AppError.FirebaseLoginError("", "", it.message.toString())
            }
    }

    override suspend fun checkAuthUser(): Boolean {
        val token = sharedPreferences.get(AppPreferencesKey.USER_ID_TOKEN, String::class.java)
        return firebaseAuth.signInWithCustomToken(token)
            .addOnFailureListener {
                throw AppError.FirebaseLoginError("", "", it.message.toString())
            }.isSuccessful
    }
}