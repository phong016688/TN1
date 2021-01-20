package com.datn.mobileapp.utils

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes private val layoutResId: Int) : Fragment(layoutResId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppUtils.logD("---------", (layoutResId % 10000).toString())
        AppUtils.logD("on create", (layoutResId % 10000).toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppUtils.logD("on view created", (layoutResId % 10000).toString())
    }

    override fun onResume() {
        super.onResume()
        AppUtils.logD("on resume", (layoutResId % 10000).toString())
        AppUtils.logD("---------", (layoutResId % 10000).toString())
    }

    override fun onPause() {
        super.onPause()
        AppUtils.logD("on pause", (layoutResId % 10000).toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        AppUtils.logD("on DestroyView", (layoutResId % 10000).toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        AppUtils.logD("on Destroy", (layoutResId % 10000).toString())
        AppUtils.logD("---------", (layoutResId % 10000).toString())
    }
}