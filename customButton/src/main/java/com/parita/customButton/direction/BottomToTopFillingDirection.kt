package com.parita.customButton.direction

import com.parita.customButton.FillButton

class BottomToTopFillingDirection : FillDirection {

    private var once = true

    override fun drawDirection(fillingButton: FillButton, animatedIntValue: Int) {
        if (once){
            fillingButton.fillingWidthValueAnimator.setIntValues(0, fillingButton.viewHeight)
            once = false
        }
        fillingButton.fillingRect.top = fillingButton.viewHeight - animatedIntValue
    }

    override fun resetDirection(fillingButton: FillButton) {
        fillingButton.fillingRect.top = fillingButton.viewHeight
    }

    override fun trigger(fillingButton: FillButton) = fillingButton.viewHeight

}