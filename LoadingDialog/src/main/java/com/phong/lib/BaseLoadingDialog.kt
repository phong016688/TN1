package com.phong.lib

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.DialogFragment


abstract class BaseLoadingDialog : DialogFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.let { dialog?.setContentView(it) }
        activity?.let { dialog?.setOwnerActivity(it) }
        dialog?.apply {
            setOnCancelListener(null)
            setOnDismissListener(null)
            setCancelable(false)
            // save instance
            savedInstanceState?.getBundle(SAVE_STATE_LOADING_DIALOG)
                ?.let { onRestoreInstanceState(it) }
            // on back click
            setOnKeyListener(DialogInterface.OnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE) {
                    dismiss()
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    companion object {
        const val SAVE_STATE_LOADING_DIALOG = "SAVE_STATE_LOADING_DIALOG"
    }
}
