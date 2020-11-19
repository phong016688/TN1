package com.phong.lib.button

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.View.OnClickListener
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import androidx.core.animation.doOnEnd
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ButtonCollapsing constructor(context: Context, attributeSet: AttributeSet?) :
    FloatingActionButton(context, attributeSet) {
    private lateinit var pointXAnim: ValueAnimator
    private lateinit var pointYAnim: ValueAnimator
    private lateinit var sizeWidthAnim: ValueAnimator
    private lateinit var sizeHeightAnim: ValueAnimator
    private val displaySize = Point(0, 0)

    init {
        context.display?.getRealSize(displaySize)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        pointXAnim = ValueAnimator.ofFloat(x, -500f).setDuration(2000L)
        pointXAnim.interpolator = AccelerateDecelerateInterpolator()
        pointXAnim.repeatCount = 1
        pointXAnim.repeatMode = ValueAnimator.RESTART
        pointXAnim.addUpdateListener {
            x = it.animatedValue as Float
            requestLayout()
        }

        pointYAnim = ValueAnimator.ofFloat(y, -500f).setDuration(2000L)
        pointYAnim.interpolator = AccelerateDecelerateInterpolator()
        pointYAnim.repeatCount = 1
        pointYAnim.repeatMode = ValueAnimator.RESTART
        pointYAnim.addUpdateListener {
            y = it.animatedValue as Float
            requestLayout()
        }

        sizeWidthAnim =
            ValueAnimator.ofInt(layoutParams.width, displaySize.x * 3).setDuration(3000L)
        sizeWidthAnim.interpolator = AccelerateDecelerateInterpolator()
        sizeWidthAnim.repeatCount = Animation.INFINITE
        sizeWidthAnim.repeatMode = ValueAnimator.RESTART
        sizeWidthAnim.addUpdateListener {
            layoutParams.width = it.animatedValue as Int
            requestLayout()
        }

        sizeHeightAnim =
            ValueAnimator.ofInt(layoutParams.height, displaySize.y * 3).setDuration(3000L)
        sizeHeightAnim.interpolator = AccelerateDecelerateInterpolator()
        sizeHeightAnim.repeatCount = Animation.INFINITE
        sizeHeightAnim.repeatMode = ValueAnimator.RESTART
        sizeHeightAnim.addUpdateListener {
            layoutParams.height = it.animatedValue as Int
            requestLayout()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        val newListener = OnClickListener {
            pointXAnim.startAnim()
            pointYAnim.startAnim()
            sizeWidthAnim.startAnim()
            sizeHeightAnim.startAnim()
            l?.onClick(it)
        }
        super.setOnClickListener(newListener)
    }

    private fun ValueAnimator.startAnim() {
        if (isStarted) return
        start()
    }
}