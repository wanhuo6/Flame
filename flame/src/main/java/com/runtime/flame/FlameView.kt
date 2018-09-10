import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.runtime.flame.R

class FlameView constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    private var progressBar: ProgressBar? = null
    private var tvTip: TextView? = null

    init {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_flame, this)
        setBackgroundResource(R.color.gray)
        progressBar = findViewById(R.id.progress)
        tvTip = findViewById(R.id.tv_tip)
    }

    fun setProgress(visibility: Int) {
        progressBar!!.visibility = visibility
    }

    fun setTip(tip: String) {
        tvTip!!.visibility = View.VISIBLE
        tvTip!!.text = tip
    }
}
