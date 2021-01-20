package com.datn.mobileapp.data.repository

import android.util.Log
import com.datn.mobileapp.data.cache.AppPreferencesKey
import com.datn.mobileapp.data.cache.AppSharedPreferences
import com.datn.mobileapp.data.remote.error.AppError
import com.datn.mobileapp.domain.model.User
import com.datn.mobileapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Single

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val sharedPreferences: AppSharedPreferences,
    private val firebaseStore: FirebaseFirestore
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

    override fun getUserProfile(userId: String): Single<User> {
        return Single.create<User> { emiter ->
            firebaseStore.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener {
                    emiter.onSuccess(
                        User(
                            it.id,
                            it.data?.get("full_name")?.toString() ?: return@addOnSuccessListener,
                            it.data?.get("phone")?.toString() ?: return@addOnSuccessListener,
                            it.data?.get("email")?.toString() ?: return@addOnSuccessListener,
                            it.data?.get("pass")?.toString() ?: return@addOnSuccessListener,
                            "",
                            it.data?.get("avatar")?.toString() ?: return@addOnSuccessListener,
                        )
                    )
                }.addOnFailureListener { emiter.onError(it) }
        }
    }
}