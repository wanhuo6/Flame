package com.runtime.flame

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup


class Flame<T> private constructor(param: FlameParam<T>) {

    init {
        initFlame(param)
    }

    private fun initFlame(param: FlameParam<T>) {
        if (param.content == null) {
            return
        }
        var mFlameView: FlameView? = when (param.content) {
            is Activity -> FlameView(param.content as Activity)
            is Fragment -> FlameView((param.content as Fragment).context!!)
            else -> {
                return
            }
        }

        when (param.type) {
            FlameType.LOADING -> {
                if (param.isRetry!=null&& param.isRetry!!){
                    mFlameView!!.setTip(param.tip!!)
                    if (param.onRetryListener != null) {
                        mFlameView.setRetryListener(param.onRetryListener!!)
                    }
                }else{
                    mFlameView!!.setProgress(View.VISIBLE)
                }

            }
            FlameType.DIALOG -> {
                var context:Context = if (param.content is Activity) param.content else (param.content as Fragment).context!!
                val builder = AlertDialog.Builder(context)
                builder.setMessage(if(TextUtils.isEmpty(param.tip)) "异常" else param.tip).setPositiveButton("知道") { dialog, _ -> dialog.dismiss() }.create().show()
                return
            }
            else -> {
                return
            }
        }
        when (param.content) {
            is Activity -> {
                remove(param.content as Activity)
                (param.content as Activity).addContentView(mFlameView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT))
            }
            is Fragment -> {
                remove(param.content as Fragment)
                var viewGroup: ViewGroup = (param.content as Fragment).view as ViewGroup
                viewGroup.addView(mFlameView)
            }
            else -> {
                return
            }
        }
    }

    class Builder<T> {
        private var param: FlameParam<T>? = null

        constructor (t: T) {
            param = FlameParam(t)
        }

        fun setType(type: FlameType): Builder<T> {
            param!!.type = type
            return this
        }

        fun setTip(tip: String): Builder<T> {
            param!!.tip = tip
            return this
        }

        fun setRetry(tip:String): Builder<T>{
            param!!.tip=tip
            param!!.isRetry=true
            return this
        }

        fun setConfirm(confirm: String): Builder<T> {
            param!!.confirm = confirm
            return this
        }

        fun setCancel(cancel: String): Builder<T> {
            param!!.cancel = cancel
            return this
        }

        fun setRetryListener(onRetryListener: FlameInterface.OnRetryListener): Builder<T> {
            param!!.onRetryListener = onRetryListener
            return this
        }

        fun destroy() {
            if (param!!.content == null) {
                return
            }
            when(param!!.content){
                is Activity-> remove(param!!.content as Activity)
                is Fragment-> remove(param!!.content as Fragment)
            }
        }

        fun crate(): Flame<T> {
            return Flame(param!!)
        }
    }

    companion object {


        fun <T> with(t: T): Builder<T> {

            return Builder(t)
        }



        private  fun remove(activity: Activity?) {
            var viewGroup = activity!!.findViewById<ViewGroup>(android.R.id.content) ?: return
            var view = viewGroup.getChildAt(viewGroup.childCount - 1)
            if (view is FlameView) {
                viewGroup.removeView(view)
            }
        }

        private fun remove(fragment: Fragment?) {
            var viewGroup: ViewGroup = fragment!!.view as ViewGroup
            var view=viewGroup.getChildAt((viewGroup).childCount - 1);
            if (view is FlameView) {
                viewGroup.removeView(view)
            }
        }
    }

}
