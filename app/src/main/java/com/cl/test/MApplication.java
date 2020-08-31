package com.cl.test;

import android.app.Application;

import com.cl.log.XConsolePrinter;
import com.cl.log.XFilePrinter;
import com.cl.log.XLogConfig;
import com.cl.log.XLogManager;


/**
 * 项目：xlog
 * 作者：Arry
 * 创建日期：2020/8/31
 * 描述：
 * 修订历史：
 */
public class MApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        XLogManager.init(new XLogConfig(){
            @Override
            public String getGlobalTag() {
                return "MApplication";
            }

            @Override
            public boolean enable() {
                return super.enable();
            }

            @Override
            public JsonParser injectJsonParser() {
                return super.injectJsonParser();
            }

            @Override
            public boolean includeThread() {
                return true;
            }

            @Override
            public int stackTraceDepth() {
                return 5;
            }
        },new XConsolePrinter(),XFilePrinter.getInstance(getApplicationContext().getCacheDir().getAbsolutePath(),0));


    }
}
