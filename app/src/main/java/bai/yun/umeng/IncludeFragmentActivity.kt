package bai.yun.umeng

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import bai.yun.umeng.fragment.FragmentOne
import bai.yun.umeng.fragment.FragmentTwo
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_include_fragment.*

class IncludeFragmentActivity : AppCompatActivity(), FragmentOne.OnFragmentInteractionListenerOne,
    FragmentTwo.OnFragmentInteractionListenerTwo {

    private lateinit var mFragmentOne: FragmentOne
    private lateinit var mFragmentTwo: FragmentTwo
    private lateinit var mFragments: List<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_include_fragment)

        mFragmentOne = FragmentOne.newInstance("fragmentOne_param1", "fragmentOne_param2")
        mFragmentTwo = FragmentTwo.newInstance("fragmentTwo_param1", "fragmentTwo_param2")
        mFragments = listOf(mFragmentOne, mFragmentTwo)

        vp_fragment.adapter = MyViewPagerAdapter(supportFragmentManager, mFragments)
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("包含 fragment 的界面")//统计页面
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("包含 fragment 的界面")
    }

    override fun onFragmentInteractionOne(uri: Uri) {


    }

    override fun onFragmentInteractionTwo(uri: Uri) {
    }

    /**
     * viewPager适配器内部类
     */
    inner class MyViewPagerAdapter(fragmentManage: FragmentManager, private val dataFragment: List<Fragment>) :
        FragmentPagerAdapter(fragmentManage) {

        override fun getItem(position: Int): Fragment {
            return dataFragment[position]
        }

        override fun getCount(): Int {
            return dataFragment.size
        }

    }

}
