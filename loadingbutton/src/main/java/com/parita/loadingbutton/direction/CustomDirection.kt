package com.parita.loadingbutton.direction

import com.parita.loadingbutton.CustomButton

interface CustomDirection {
    fun drawDirection(cutomButton: CustomButton, animatedIntValue: Int)
    fun resetDirection(cutomButton: CustomButton)
    fun trigger(cutomButton: CustomButton): Int
}