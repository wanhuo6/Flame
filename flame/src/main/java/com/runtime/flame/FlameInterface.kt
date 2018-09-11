package com.runtime.flame
interface FlameInterface {

    interface OnRetryListener {
        fun onRetryClick()
    }

    interface OnTipListener {
        fun onConfirm()
        fun onCancel()
    }

}
