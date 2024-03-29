package com.cl.log;


import androidx.annotation.NonNull;

import com.cl.log.util.XStackTraceUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 项目：xlog
 * 作者：Arry
 * 创建日期：2020/8/31
 * 描述：
 * 修订历史：
 */
public class XLog {


    private static final String HI_LOG_PACKAGE;

    static {
        String className = XLog.class.getName();
        HI_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    public static void v(Object... contents) {
        log(XLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(XLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(XLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(XLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(XLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(XLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(XLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(XLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(XLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(XLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(XLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(XLogType.A, tag, contents);
    }

    public static void log(@XLogType.TYPE int type, Object... contents) {
        log(type, XLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@XLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(XLogManager.getInstance().getConfig(), type, tag, contents);
    }

    /**
     * log打印
     */
    public static void log(@NonNull XLogConfig config, @XLogType.TYPE int type, @NonNull String tag, Object... contents) {
        //如果LogKit是关闭的,直接return
        if (!config.enable()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        //拼接线程信息
        if (config.includeThread()) {
            //对当前的线程进行信息的格式化
            String threadInfo = XLogConfig.HI_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }

        //拼接堆栈信息
        if (config.stackTraceDepth() > 0) {
            //获取堆栈信息
            StackTraceElement[] traceElements = XStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), HI_LOG_PACKAGE, config.stackTraceDepth());
            //格式化堆栈信息
            String traceInfo = XLogConfig.HI_STACK_TRACE_FORMATTER.format(traceElements);
            sb.append(traceInfo).append("\n");
        }


        //拼接contents信息
        String body =  parseBody(contents,config);
        if (body != null) {//替换转义字符\
            body = body.replace("\\\"", "\"");
        }
        sb.append(body);

        //拿到初始时候自己定义的打印器,然后进行打印,看是打印到文件,还是控制台,还是文件中
        List<XLogPrinter> printerList = config.printers() !=null ? Arrays.asList(config.printers()) : XLogManager.getInstance().getPrinters();
        if (printerList == null) {
            return;
        }
        //打印log
        for(XLogPrinter printer:printerList){
            printer.print(config,type,tag,sb.toString());
        }

    }


    private static String parseBody(@NonNull Object[] contents, @NonNull XLogConfig config) {
        if (config.injectJsonParser() != null) {
            //只有一个数据且为String的情况下不再进行序列化
            if (contents.length == 1 && contents[0] instanceof String) {
                return (String) contents[0];
            }
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


}
