package com.datn.mobileapp.domain.repository

interface AuthRepository {
    suspend fun loginFirebase(userName: String, password: String)
}