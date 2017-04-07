package me.wjg.weexl.appweex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IWXRenderListener {

    // WeexSDK 渲染引擎, 单例运行, 通过自定义字符串和当前页面捆绑
    private WXSDKInstance mWXSDKInstance;

    private static String TEST_URL = "http://dotwe.org/raw/dist/6fe11640e8d25f2f98176e9643c08687.bundle.js";
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = (FrameLayout) findViewById(R.id.container);

        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);

        /**
         * WXSample 可以替换成自定义的字符串，针对埋点有效。
         * template 是.we transform 后的 js文件。
         * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
         * jsonInitData 可以为空。
         * width 为-1 默认全屏，可以自己定制。
         * height =-1 默认全屏，可以自己定制。
         * APPEND_ASYNC 渲染策略, 异步渲染
         */
        // 从网络载入
//        HashMap<String, Object> options = new HashMap<>();
//        options.put(WXSDKInstance.BUNDLE_URL, TEST_URL);
//        mWXSDKInstance.renderByUrl("WXSample", TEST_URL, options, null, WXRenderStrategy.APPEND_ONCE);

        // 从本地路径载入 Js页面的方法
        HashMap<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, "file://build/hello.js");
        // build/hello.js
        mWXSDKInstance.render("Sample", WXFileUtils.loadAsset("dist/main.js", this), null, null, WXRenderStrategy.APPEND_ONCE);
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        mContainer.addView(view);

//        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }

    @Override
    protected void onStart() {
        super.onStart();
//        if(mWXSDKInstance != null) {
//            mWXSDKInstance.onActivityStart();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }
}
