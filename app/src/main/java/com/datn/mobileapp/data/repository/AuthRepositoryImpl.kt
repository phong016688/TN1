package com.datn.mobileapp.data.repository

import com.datn.mobileapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImpl(private val firebaseAuth: FirebaseAuth) : AuthRepository {

    override suspend fun loginFirebase(userName: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener {}
    }
}