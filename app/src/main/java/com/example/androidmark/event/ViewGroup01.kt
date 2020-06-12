package com.example.androidmark.event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

class ViewGroup01 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {




    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        println("${javaClass.simpleName} : dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        println("${javaClass.simpleName} : onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("${javaClass.simpleName} : onTouchEvent")
        return super.onTouchEvent(event)
    }


}