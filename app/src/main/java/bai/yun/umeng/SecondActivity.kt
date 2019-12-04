package bai.yun.umeng

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn_stat_fragment.setOnClickListener {
            startActivity(Intent(this, IncludeFragmentActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("第二个界面 Second")//统计页面
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("第二个界面 Second")
    }
}
