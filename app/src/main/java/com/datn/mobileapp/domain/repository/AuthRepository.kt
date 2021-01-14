package com.datn.mobileapp.domain.repository

import com.datn.mobileapp.domain.model.User
import io.reactivex.rxjava3.core.Single

interface AuthRepository {
    suspend fun loginFirebase(userName: String, password: String)
    suspend fun checkAuthUser(): Boolean
    fun getUserProfile(userId: String): Single<User>
}