package com.runtime.flame

class FlameParam<T>{
    val content: T?
    var onRetryListener:FlameInterface.OnRetryListener?=null
    var type: Int = 0
    var tip: String? = null
    var confirm: String? = null
    var cancel: String? = null


    constructor(con: T) {
        content = con
    }
}
