package com.datn.mobileapp.utils

import android.content.Context
import android.util.TypedValue

object AppUtils {
    public fun convertFromDpToPixel(dp: Float, context: Context) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp,
        context.resources.displayMetrics
    )
}