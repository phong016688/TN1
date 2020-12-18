package com.datn.mobileapp.helpers

import java.util.regex.Pattern

object ValidatorHelper {
    val PASSWORD_PATTERN = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[a-zA-Z])" +
                //  "(?=.*[@#$%^&+=!])" +
                "(?=\\S+$)" +
                ".{4,}" +
                "$"
    )
}
