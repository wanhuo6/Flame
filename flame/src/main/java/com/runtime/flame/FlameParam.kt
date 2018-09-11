package com.runtime.flame
import android.app.Activity
import android.support.v4.app.Fragment

class FlameParam {
    val activity: Activity?
    val fragment: Fragment?
    lateinit var onRetryListener:FlameInterface.OnRetryListener
    var type: Int = 0
    var tip: String? = null
    var confirm: String? = null
    var cancel: String? = null

    constructor(act: Activity) {
        activity = act
        fragment = null
    }

    constructor(fgt: Fragment) {
        fragment = fgt
        activity = null
    }
}
