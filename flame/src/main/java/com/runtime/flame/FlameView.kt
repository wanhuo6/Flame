package com.runtime.flame

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_flame.view.*

class FlameView constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_flame, this)
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setBackgroundResource(R.color.flame_bg)
        alpha = 0.92f
        isClickable = true
        isFocusable=true
        isFocusableInTouchMode=true
        requestFocus()
    }

    fun setProgress(visibility: Int) {
        progressBar!!.visibility = visibility
    }

    fun setTip(tip: String) {
        tvTip!!.visibility = View.VISIBLE
        tvTip!!.text = tip
    }

    fun setRetryListener(onRetryListener: FlameInterface.OnRetryListener) {
        if (onRetryListener == null) {
            return
        }
        ivError.visibility = View.VISIBLE
        this!!.setOnClickListener { onRetryListener.onRetryClick() }
    }

}
