package com.runtime.flame

class FlameParam<T>{
    val content: T?
    lateinit var onRetryListener:FlameInterface.OnRetryListener
    var type: Int = 0
    var tip: String? = null
    var confirm: String? = null
    var cancel: String? = null


    constructor(con: T) {
        content = con
    }
}
