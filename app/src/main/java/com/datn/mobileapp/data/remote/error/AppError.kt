package com.datn.mobileapp.data.remote.error

sealed class AppError(
    val code: String,
    val appMessage: String,
    message: String
) : Exception(message) {
    class FirebaseLoginError(
        code: String,
        appMessage: String,
        message: String
    ) : AppError(code, appMessage, message)
}