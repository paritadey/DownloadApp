package com.parita.customButton.direction

import com.parita.customButton.FillButton

class LeftToRightFillingDirection :FillDirection {
    override fun drawDirection(fillingButton: FillButton, animatedIntValue: Int) {
        fillingButton.fillingRect.right = animatedIntValue
    }

    override fun resetDirection(fillingButton: FillButton) {
        fillingButton.fillingRect.right = 0
    }

    override fun trigger(fillingButton: FillButton) = fillingButton.viewWidth

}