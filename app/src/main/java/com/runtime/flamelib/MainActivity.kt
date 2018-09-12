package com.runtime.flamelib

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.runtime.flame.Flame
import com.runtime.flame.FlameType

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Flame.with(this).setTip("你说的啥").setType(FlameType.DIALOG).crate()
        Handler().postDelayed({Flame.with(this).destroy()} ,5000)

    }
}
