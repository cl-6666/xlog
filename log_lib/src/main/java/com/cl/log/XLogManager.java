package com.cl.log;

import androidx.annotation.NonNull;

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
    private static XLogManager instance;
    private List<XLogPrinter> printers = new ArrayList<>();

    private XLogManager(XLogConfig config, XLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static XLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull XLogConfig config, XLogPrinter... printers) {
        instance = new XLogManager(config, printers);
    }

    public XLogConfig getConfig() {
        return config;
    }

    public List<XLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(XLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(XLogPrinter printer) {
        if (printers != null) {
            printers.remove(printer);
        }
    }



}
