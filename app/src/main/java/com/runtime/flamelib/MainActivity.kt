package com.runtime.flamelib

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.runtime.flame.Flame
import com.runtime.flame.FlameInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Flame.with(this).setRetry("LOG_CLICK").setRetryListener(object : FlameInterface.OnRetryListener {
            override fun onRetryClick() {
                Log.e("====","CLICK")
            }
        }).crate()
        Handler().postDelayed({Flame.with(this).destroy()} ,10000)
        tvText.setOnClickListener {Toast.makeText(this,"TEXT_CLICK",Toast.LENGTH_LONG).show()}

    }
}
