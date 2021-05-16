package com.parita.customButton.direction

import com.parita.customButton.FillButton

class TopToBottomFillingDirection : FillDirection {

    private var once = true

    override fun drawDirection(fillingButton: FillButton, animatedIntValue: Int) {
        if (once){
            fillingButton.fillingWidthValueAnimator.setIntValues(0, fillingButton.viewHeight)
            once = false
        }
        fillingButton.fillingRect.bottom = animatedIntValue
    }

    override fun resetDirection(fillingButton: FillButton) {
        fillingButton.fillingRect.bottom = 0
    }

    override fun trigger(fillingButton: FillButton) = fillingButton.viewHeight

}