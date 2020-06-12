package com.example.androidmark.event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class View01 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        println("${javaClass.simpleName} : dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("${javaClass.simpleName} : onTouchEvent")
        return super.onTouchEvent(event)
    }


}