package com.datn.mobileapp.utils.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.datn.mobileapp.R
import com.google.android.material.card.MaterialCardView

class CircleImageView(context: Context, attrRes: AttributeSet?) : FrameLayout(context, attrRes) {
    private val cardView: MaterialCardView
    private val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

    init {
        cardView = LayoutInflater.from(context)
            .inflate(R.layout.layout_circle_image, null, false) as MaterialCardView
        cardView.strokeColor = Color.CYAN
        setBackgroundColor(Color.TRANSPARENT)
        addView(cardView)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        layoutParams.width = widthMeasureSpec - 20
        layoutParams.height = heightMeasureSpec - 20
        layoutParams.setMargins(10, 10, 10, 10)
        cardView.layoutParams = layoutParams
        cardView.radius = widthMeasureSpec / 2f
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun loadUrl(url: String) {
        if (url.isEmpty()) return
        cardView.findViewById<ImageView>(R.id.image_view).load(url) {
            crossfade(true)
            placeholder(R.drawable.waiting)
            transformations(CircleCropTransformation())
        }
    }
}