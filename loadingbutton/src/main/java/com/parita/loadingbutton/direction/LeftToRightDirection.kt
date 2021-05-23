package com.parita.loadingbutton.direction

import com.parita.loadingbutton.CustomButton

class LeftToRightDirection: CustomDirection {
    override fun drawDirection(cutomButton: CustomButton, animatedIntValue: Int) {
        cutomButton.fillingRect.right = animatedIntValue
    }

    override fun resetDirection(cutomButton: CustomButton) {
        cutomButton.fillingRect.right = 0
    }

    override fun trigger(cutomButton: CustomButton)= cutomButton.viewWidth

}
