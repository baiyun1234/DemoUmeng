package bai.yun.umeng

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity -> "
    private var mTest: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate()")

        btn_start.setOnClickListener {
            Log.d(TAG, "点击了按钮")
        }

        btn_catch.setOnClickListener {
            Log.d(TAG, "点击了奔溃按钮")
            Log.d(TAG, this.mTest!![3].toString())
        }

    }

}
