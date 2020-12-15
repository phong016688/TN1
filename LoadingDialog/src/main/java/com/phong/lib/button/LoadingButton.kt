package com.phong.lib.button

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout.LayoutParams.WRAP_CONTENT
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView

class LoadingButton(context: Context, attributes: AttributeSet?) :
    FrameLayout(context, attributes), View.OnClickListener {

    private val cardView: CardView = CardView(context)
    private val loadingView: ProgressBar = ProgressBar(context)
    private val textView: TextView = TextView(context)
    private lateinit var animWidth: ValueAnimator
    private var centerX: Int = 0
    private val interpolatorAnim = AccelerateDecelerateInterpolator()

    init {
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.textSize = 30f
        textView.setTextColor(Color.WHITE)
        textView.gravity = Gravity.CENTER
        textView.text = "Click me"
        textView.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            setPadding(10, 20, 10, 20)
        }

        loadingView.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

        cardView.radius = 50f
        cardView.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            .apply {
                setMargins(6, 6, 6, 6)
                gravity = Gravity.CENTER
            }
        cardView.elevation = 5f
        cardView.setCardBackgroundColor(Color.rgb(141, 121, 151))

        cardView.addView(textView)

        layoutParams = LayoutParams(context, attributes).apply { gravity = Gravity.CENTER }
        background = null
        setBackgroundColor(Color.TRANSPARENT)
        addView(cardView)
        addView(loadingView)
        loadingView.visibility = GONE

        centerX = x.toInt() + width / 2

        Log.d("#######", width.toString())
        animWidth = ValueAnimator.ofInt(0, width).apply {
            duration = 3000L
            interpolator = interpolatorAnim
            addUpdateListener {
                Log.d("#######", it.animatedValue.toString())
                val value = width - (it.animatedValue as? Int ?: width)
                if (value <= loadingView.width + 50) {
                    loadingView.visibility = View.VISIBLE
                    cardView.visibility = View.GONE
                } else {
                    layoutParams.width = value
                    x = centerX.toFloat() - value / 2;
                    requestLayout()
                }
                requestLayout()
            }
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        cardView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (animWidth.isStarted) return
        animWidth.repeatCount = 2
        animWidth.repeatMode = ValueAnimator.REVERSE
        animWidth.start()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}