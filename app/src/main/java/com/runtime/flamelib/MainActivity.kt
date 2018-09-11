package com.runtime.flamelib

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.runtime.flame.Flame

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Flame.with(this).setType(Flame.TYPE_TIP).setTip("dd").crate()
        Handler().postDelayed({Flame.remove(this)} ,3000)

    }
}
