package com.cl.test;

import android.app.Application;

import com.cl.log.XConsolePrinter;
import com.cl.log.XFilePrinter;
import com.cl.log.XLogConfig;
import com.cl.log.XLogManager;
import com.google.gson.Gson;


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

        //初始化日志框架
        XLogConfig logConfig = new XLogConfig.Builder()
                //全局TAG
                .setGlobalTag("TAG")
                //是否包含线程信息
                .setWhetherThread(true)
                //Xlog是否可用
                .setWhetherToPrint(true)
                //是否存储日志到本地  log文件的有效时长，单位毫秒，<=0表示一直有效
                .setStoreLog(true,0)
                //堆栈的深度
                .setStackDeep(5)
                .setInjectSequence(new XLogConfig.JsonParser() {
                    @Override
                    public String toJson(Object src) {
                        String json = new Gson().toJson(src);
                        return json;
                    }
                }).build();

        XLogManager.getInstance().init(logConfig, new XConsolePrinter());
    }
}
