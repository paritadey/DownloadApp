package com.parita.loadingbutton.direction

import com.parita.loadingbutton.CustomButton

class RightToLeftDirection : CustomDirection {
    override fun drawDirection(cutomButton: CustomButton, animatedIntValue: Int) {
        cutomButton.fillingRect.left = cutomButton.viewWidth - animatedIntValue
    }

    override fun resetDirection(cutomButton: CustomButton) {
        cutomButton.fillingRect.left=0
    }

    override fun trigger(cutomButton: CustomButton) = cutomButton.viewWidth
}
