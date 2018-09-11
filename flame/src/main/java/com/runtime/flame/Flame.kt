package com.runtime.flame

import android.app.Activity
import android.support.v4.app.Fragment
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
            TYPE_DEFAULT -> mFlameView!!.setProgress(View.VISIBLE)
            TYPE_TIP -> {
                mFlameView!!.setTip(param.tip!!)
                if (param.onRetryListener != null) {
                    mFlameView.setRetryListener(param.onRetryListener)
                }
            }
            else -> {
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

        fun setType(type: Int): Builder<T> {
            param!!.type = type
            return this
        }

        fun setTip(tip: String): Builder<T> {
            param!!.tip = tip
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

        fun crate(): Flame<T> {
            return Flame(param!!)
        }
    }

    companion object {

        const val TYPE_DEFAULT = 0//progress
        const val TYPE_TIP = 1

        fun <T> with(t: T): Builder<T> {

            return Builder(t)
        }

        fun<T> remove(t:T) {
            if (t == null) {
                return
            }
            when(t){
                is Activity-> remove(t as Activity)
                is Fragment-> remove(t as Fragment)
            }

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
