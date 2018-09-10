import android.app.Activity
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup


class Flame private constructor(param: FlameParam) {

    init {
        initFlame(param)
    }

    private fun initFlame(param: FlameParam) {
        if (param.activity == null && param.fragment == null) {
            return
        }
        val mFlameView = FlameView(param.activity ?: param.fragment!!.context!!)
        when (param.type) {
            TYPE_DEFAULT -> mFlameView.setProgress(View.VISIBLE)
            TYPE_TIP -> mFlameView.setTip(param.tip!!)
            else -> {
            }
        }
        if (param.activity != null) {
            remove(param.activity)
            param.activity.addContentView(mFlameView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT))
        } else if (param.fragment != null) {
            remove(param.fragment)
            var viewGroup: ViewGroup = param.fragment.view as ViewGroup
            viewGroup.addView(mFlameView)
        }

    }

    class Builder {
        private var param: FlameParam? = null

        constructor(activity: Activity) {
            param = FlameParam(activity)
        }

        constructor(fragment: Fragment) {
            param = FlameParam(fragment)
        }

        fun setType(type: Int): Builder {
            param!!.type = type
            return this
        }

        fun setTip(tip: String): Builder {
            param!!.tip = tip
            return this
        }

        fun setConfirm(confirm: String): Builder {
            param!!.confirm = confirm
            return this
        }

        fun setCancel(cancel: String): Builder {
            param!!.cancel = cancel
            return this
        }

        fun crate(): Flame {
            return Flame(param!!)
        }
    }

    companion object {

        const val TYPE_DEFAULT = 0//progress
        const val TYPE_TIP = 1

        fun with(activity: Activity): Builder {

            return Builder(activity)
        }

        fun with(fragment: Fragment): Builder {

            return Builder(fragment)
        }

        fun remove(activity: Activity?) {
            if (activity == null) {
                return
            }
            val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content) ?: return
            val view = viewGroup.getChildAt(viewGroup.childCount - 1)
            if (view is FlameView) {
                viewGroup.removeView(view)
            }
        }

        fun remove(fragment: Fragment?) {
            if (fragment == null) {
                return
            }
            var viewGroup:ViewGroup=fragment.view as ViewGroup
            if(viewGroup.getChildAt((viewGroup).childCount - 1)is FlameView){
                viewGroup.removeViewAt(viewGroup.childCount - 1)
            }
        }
    }

}
