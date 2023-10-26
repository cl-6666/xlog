package com.cl.log;

import androidx.annotation.NonNull;

import com.cl.log.util.AppGlobal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目：xlog
 * 作者：Arry
 * 创建日期：2020/8/31
 * 描述：
 * 修订历史：
 */
public class XLogManager {

    private XLogConfig config;
    private List<XLogPrinter> printers = new ArrayList<>();

    private XLogManager() {
    }

    public static class SingleHolder {
        public static volatile XLogManager instance = new XLogManager();
    }

    public static XLogManager getInstance() {
        return SingleHolder.instance;
    }

    public void init(@NonNull XLogConfig config, XLogPrinter... printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
        //是否保存日志到本地
        if (config.getStoreLog()){
            this.printers.add(XFilePrinter.getInstance(AppGlobal.getInstance().getCacheDir().getAbsolutePath()
                    , 0));
        }
    }

    public XLogConfig getConfig() {
        check();
        return config;
    }

    public List<XLogPrinter> getPrinters() {
        check();
        return printers;
    }

    public void addPrinter(XLogPrinter printer) {
        check();
        printers.add(printer);
    }

    public void removePrinter(XLogPrinter printer) {
        check();
        if (printers != null) {
            printers.remove(printer);
        }
    }

    private void check() {
        if (config == null || printers == null) {
            throw new RuntimeException("XLogManager 的 init 还未初始化");
        }
    }

}
