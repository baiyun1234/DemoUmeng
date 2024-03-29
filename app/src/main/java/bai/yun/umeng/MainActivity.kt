package bai.yun.umeng

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bai.yun.umeng.excaption.CustomException
import bai.yun.umeng.util.DateUtil
import com.umeng.analytics.MobclickAgent
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

        btn_custom_event.setOnClickListener {
            Log.d(TAG, "点击了普通自定义事件(登录名称：xiaoBai)")
            MobclickAgent.onEvent(this, "loginName", "xiaoBai")
        }

        btn_custom_event_map.setOnClickListener {
            Log.d(TAG, "点击了Map自定义事件(个人信息：firstName，age, sn等)")
            val personInfoMap = HashMap<String, String>()
            personInfoMap["firstName"] = "bai"
            personInfoMap["lastName"] = "yunYan"
            personInfoMap["age"] = "18"
            personInfoMap["stature"] = "165"
            personInfoMap["sn"] = Build.SERIAL
            personInfoMap["time"] = DateUtil.getCurDateStr("yyyy-MM-dd HH:mm:ss")
            MobclickAgent.onEvent(this, "personInfo", personInfoMap)
        }

        btn_custom_exception.setOnClickListener {
            Log.d(TAG, "点击了自定义异常")
            //适用于自己捕捉了异常，需要上送到友盟
            val customException = CustomException("001", "我是自定义错误")
            MobclickAgent.reportError(this, customException)
        }

        btn_second.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        btn_fragment.setOnClickListener {
            Log.d(TAG, "点击了跳转到fragment")
            startActivity(Intent(this, IncludeFragmentActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("主界面 Main")//统计页面
//        MobclickAgent.onResume(this)//统计时长，初始化时集成模式设置为AUTH可自定统计时长，不需要手动写这行代码
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("主界面 Main")//统计页面,与MobclickAgent.onPageStart()成对出现
    }

}
