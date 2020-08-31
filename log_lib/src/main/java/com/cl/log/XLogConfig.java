package com.cl.log;

/**
 * 项目：xlog
 * 作者：Arry
 * 创建日期：2020/8/31
 * 描述：
 * 修订历史：
 */
public class XLogConfig {


    static int MAX_LEN = 512;
    static XThreadFormatter HI_THREAD_FORMATTER = new XThreadFormatter();
    static XStackTraceFormatter HI_STACK_TRACE_FORMATTER = new XStackTraceFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "XLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public XLogPrinter[] printers() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object src);
    }



}
