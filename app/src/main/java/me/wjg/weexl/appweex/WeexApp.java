package me.wjg.weexl.appweex;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

/**
 * Created by Administrator on 2017/4/6.
 */

public class WeexApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InitConfig config = new InitConfig.Builder()
                                                    .setImgAdapter(new ImageAdapter())
                                                    .build();
        WXSDKEngine.initialize(this, config);
    }
}
