package com.parita.customButton.direction

import com.parita.customButton.FillButton

class RightToLeftFillingDirection : FillDirection {
    override fun drawDirection(fillingButton: FillButton, animatedIntValue: Int){
        fillingButton.fillingRect.left = fillingButton.viewWidth - animatedIntValue
    }

    override fun resetDirection(fillingButton: FillButton) {
        fillingButton.fillingRect.left = 0
    }

    override fun trigger(fillingButton: FillButton) = fillingButton.viewWidth

}