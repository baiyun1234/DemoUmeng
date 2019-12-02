package bai.yun.umeng;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

import java.util.Arrays;

public class MyApplication extends Application {

    private String TAG = "MyApplication -> ";
    private static final String appKey = "5de48cd84ca357f386000f3f";

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化友盟SDK
         */
        //如果您已经在AndroidManifest.xml中配置过appkey和channel值，可以调用此版本初始化函数
        UMConfigure.init(this, appKey, "baiBaiTestChannel", UMConfigure.DEVICE_TYPE_PHONE, null);

        //选用AUTO页面采集模式（4.0及以上），除此之外还有
        //MANUAL模式（4.0以下）：手动模式，需要手动在各个Activity的onResume和onPause方法调用MobclickAgent.onResume/MobclickAgent.onPause
        // -在AUTO或MANUAL模式下，如果需要对非Activity页面，如Fragment、自定义View等非标准页面进行统计。需要通过MobclickAgent.onPageStart/MobclickAgent.onPageEnd接口在合适的时机进行页面统计。
        // -一次成对的 onPageStart -> onPageEnd 调用，对应一次非Activity页面(如：Fragment)生命周期统计。
        // -public static void onPageStart(String viewName);
        // -public static void onPageEnd(String viewName);viewName是自定义界面名
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);

        // 打开统计SDK调试模式，主要用于测试设备，测试设备时必须设置为true
//        UMConfigure.setLogEnabled(true);

        //设备信息，如果要测试设备，需要在友盟服务器添加测试设备
        Log.d(TAG, "设备信息：" + Arrays.toString(getTestDeviceInfo(this.getApplicationContext())));

    }

    public static String[] getTestDeviceInfo(Context context) {
        String[] deviceInfo = new String[2];
        try {
            if (context != null) {
                deviceInfo[0] = DeviceConfig.getDeviceIdForGeneral(context);
                deviceInfo[1] = DeviceConfig.getMac(context);
            }
        } catch (Exception e) {
        }
        return deviceInfo;
    }


}
