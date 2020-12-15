package com.datn.mobileapp.domain.model

data class User(
    val mId: String,
    val fullName: String,
    val mPhone: String,
    val mEmail: String,
    val mPass: String,
    val confirmPass: String
)
