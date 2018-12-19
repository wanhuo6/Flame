package com.runtime.flamelib

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.runtime.flame.Flame
import com.runtime.flame.FlameInterface

/**
 * description :
 * author : LiuHuiJie
 * created on : 2018/12/19
 */
class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onStart() {
        super.onStart()
        Flame.with(this).setRetry("LOG_CLICK").setRetryListener(object : FlameInterface.OnRetryListener {
            override fun onRetryClick() {
                Log.e("====", "CLICK")
            }
        }).crate()
        Handler().postDelayed({ Flame.with(this).destroy() }, 10000)
    }


}