package com.parita.loadingbutton

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import com.parita.loadingbutton.direction.CustomDirection
import com.parita.loadingbutton.direction.LeftToRightDirection

class CustomButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0): AppCompatButton(context, attrs, defStyle){


    internal var viewWidth: Int = 0
    internal var viewHeight: Int = 0
    internal val fillingRect = Rect()

    private val fillingColor: Int
    private val fillingAlpha: Int
    private val fillingDuration: Long

    private val fillingPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var drawProgress = false

    open var onButtonFilled: (() -> Unit)? = null
    var cutomDirection: CustomDirection = LeftToRightDirection()
    val customWidthValueAnimator = ValueAnimator()

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CustomButton, defStyle, 0).apply {
            fillingColor = getColor(R.styleable.CustomButton_fillColor, resources.getColor(R.color.purple_500))
            fillingAlpha = getInt(R.styleable.CustomButton_fillAlpha, 128)
            fillingDuration = getInt(R.styleable.CustomButton_fillDuration, 1500).toLong()

        }.recycle()
        fillingPaint.color = fillingColor
        fillingPaint.alpha = fillingAlpha
        customWidthValueAnimator.duration = fillingDuration
        post {
            viewWidth = width
            viewHeight = height
            fillingRect.bottom = viewHeight
            fillingRect.right = viewWidth
            customWidthValueAnimator.setIntValues(0, viewWidth)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (drawProgress && onButtonFilled != null){
            canvas?.drawRect(fillingRect, fillingPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                drawProgress = true
                startFillingAnimation()
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                drawProgress = false
                stopFillingAnimation()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun startFillingAnimation(){
        stopFillingAnimation()
        customWidthValueAnimator.apply {
            addUpdateListener {
                val intValue = it.animatedValue as Int
                cutomDirection.drawDirection(this@CustomButton, intValue)
                invalidate()
                if (intValue == cutomDirection.trigger(this@CustomButton)){
                    onButtonFilled?.invoke()
                }
            }
        }.start()
    }

    private fun stopFillingAnimation(){
        customWidthValueAnimator.cancel()
        customWidthValueAnimator.removeAllUpdateListeners()
        cutomDirection.resetDirection(this)
        invalidate()
    }

}