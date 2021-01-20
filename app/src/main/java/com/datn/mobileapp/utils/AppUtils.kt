package com.datn.mobileapp.utils

import android.content.Context
import android.util.Log
import android.util.TypedValue
import com.datn.mobileapp.BuildConfig

object AppUtils {
    public fun convertFromDpToPixel(dp: Float, context: Context) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp,
        context.resources.displayMetrics
    )

    fun logD(string: String, tag: String = "") {
        if (BuildConfig.DEBUG) {
            Log.d("###-$tag", string)
        }
    }
}