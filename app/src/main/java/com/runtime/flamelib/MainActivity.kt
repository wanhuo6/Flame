package com.runtime.flamelib

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      /*  Flame.with(this).setRetry("LOG_CLICK").setRetryListener(object : FlameInterface.OnRetryListener {
            override fun onRetryClick() {
                Log.e("====","CLICK")
            }
        }).crate()
        Handler().postDelayed({Flame.with(this).destroy()} ,10000)*/
       // tvText.setOnClickListener {Toast.makeText(this,"TEXT_CLICK",Toast.LENGTH_LONG).show()}
        initFragment()

    }

    private fun initFragment() {

    }
}
