package com.phong.lib

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout

class LoadingDialog : BaseLoadingDialog() {
    private lateinit var operatingAnim: Animation
    private lateinit var waitingAnim: Animation
    private lateinit var graduallyTextView: GraduallyTextView
    private lateinit var backgroundView: LinearLayout
    private lateinit var mouseImageView: ImageView
    private lateinit var waitingImageView: ImageView
    private var mainDialog: Dialog? = null
    private var isClickCancelAble = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val linearInterpolator = LinearInterpolator()
        waitingAnim = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        waitingAnim.repeatCount = Animation.INFINITE
        waitingAnim.duration = 4000
        waitingAnim.interpolator = linearInterpolator


        operatingAnim = RotateAnimation(
            360f,
            0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        operatingAnim.repeatCount = Animation.INFINITE
        operatingAnim.duration = 2000
        operatingAnim.interpolator = linearInterpolator

        return Dialog(requireActivity(), R.style.loading_dialog).apply {
            setContentView(R.layout.loading_main)
            setCanceledOnTouchOutside(isClickCancelAble)
            window?.setGravity(Gravity.CENTER)
            window?.decorView?.let { view ->
                backgroundView = view.findViewById(R.id.background)
                mouseImageView = view.findViewById(R.id.mouse)
                waitingImageView = view.findViewById(R.id.waiting)
                graduallyTextView = view.findViewById(R.id.graduallyTextView)
            }
        }.also { mainDialog = it }
    }

    override fun onResume() {
        super.onResume()
        mouseImageView.animation = operatingAnim
        waitingImageView.animation = waitingAnim
        graduallyTextView.startLoading()
    }

    override fun onPause() {
        operatingAnim.cancel()
        mouseImageView.clearAnimation()
        waitingAnim.cancel()
        waitingImageView.clearAnimation()
        graduallyTextView.stopLoading()
        super.onPause()
    }

    override fun onDestroyView() {
        val dialog = mainDialog ?: return
        if (dialog.isShowing) {
            dialog.dismiss()
            mainDialog = null
        }
        super.onDestroyView()
    }

    fun setBackgroundColor(color: Int) {
        backgroundView.setBackgroundColor(color)
        mainDialog?.window?.decorView?.requestLayout()

    }

    companion object {
        val TAG = this::class.java.simpleName
    }
}