package com.parita.customButton.direction

import com.parita.customButton.FillButton

interface FillDirection {
    fun drawDirection(fillingButton: FillButton, animatedIntValue: Int)
    fun resetDirection(fillingButton: FillButton)
    fun trigger(fillingButton: FillButton): Int
}