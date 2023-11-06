package com.cl.log;

import com.cl.log.util.AppGlobal;

/**
 * 项目：xlog
 * 作者：Arry
 * 创建日期：2020/8/31
 * 描述：
 * 修订历史：
 */
public class XLogConfig {

    //每行的最大长度为512
    static int MAX_LEN = 512;
    //线程信息格式化对象
    static XThreadFormatter HI_THREAD_FORMATTER = new XThreadFormatter();
    //堆栈信息格式化对象
    static XStackTraceFormatter HI_STACK_TRACE_FORMATTER = new XStackTraceFormatter();


    private String globalTag;
    private boolean whetherThread;
    private boolean whetherToPrint;
    private int stackDeep;
    private JsonParser injectSequence;
    //是否存储日志到本地
    private boolean storeLog;
    //本地日志保存多久
    private long retentionTime;
    //本地日志存储地址
    private String logAddress = AppGlobal.getInstance().getCacheDir().getAbsolutePath();

    public XLogConfig(Builder builder) {
        this.globalTag = builder.globalTag;
        this.whetherThread = builder.whetherThread;
        this.whetherToPrint = builder.whetherToPrint;
        this.stackDeep = builder.stackDeep;
        this.injectSequence = builder.injectSequence;
        this.storeLog = builder.storeLog;
    }

    public XLogConfig() {
    }

    /**
     * 外界注入对象的序列化
     */
    public JsonParser injectJsonParser() {
        return injectSequence;
    }

    /**
     * 是否存储日志到本地
     */
    public boolean getStoreLog() {
        return storeLog;
    }

    /**
     * log文件的有效时长，单位毫秒，<=0表示一直有效
     */
    public long getRetentionTime() {
        return retentionTime;
    }

    /**
     * 存储日志到指定位置
     */
    public String getLogAddress() {
        return logAddress;
    }

    /**
     * 全局的tag
     */
    public String getGlobalTag() {
        return globalTag;
    }

    /**
     * Xlog是否可用
     */
    public boolean enable() {
        return whetherToPrint;
    }

    /**
     * 是否包含线程信息
     */
    public boolean includeThread() {
        return whetherThread;
    }

    /**
     * 堆栈的深度
     */
    public int stackTraceDepth() {
        return stackDeep;
    }

    /**
     * 打印器
     */
    public XLogPrinter[] printers() {
        return null;
    }

    //接口
    public interface JsonParser {
        String toJson(Object src);
    }


    public static class Builder {

        //全局TAG
        private String globalTag = "XLog";
        //是否包含线程信息
        private boolean whetherThread = false;
        //Xlog是否可用
        private boolean whetherToPrint = true;
        //堆栈的深度
        private int stackDeep = 5;
        private JsonParser injectSequence;
        private boolean storeLog = false;
        private long retentionTime = 0;
        private String logAddress;

        public Builder setGlobalTag(String globalTag) {
            this.globalTag = globalTag;
            return this;
        }

        public Builder setWhetherThread(boolean whetherThread) {
            this.whetherThread = whetherThread;
            return this;
        }

        public Builder setWhetherToPrint(boolean whetherToPrint) {
            this.whetherToPrint = whetherToPrint;
            return this;
        }

        public Builder setStackDeep(int stackDeep) {
            this.stackDeep = stackDeep;
            return this;
        }

        public Builder setInjectSequence(JsonParser injectSequence) {
            this.injectSequence = injectSequence;
            return this;
        }

        public Builder setStoreLog(boolean storeLog, long retentionTime) {
            this.storeLog = storeLog;
            this.retentionTime = retentionTime;
            return this;
        }

        public Builder setStoreLog(boolean storeLog, long retentionTime, String logAddress) {
            this.storeLog = storeLog;
            this.retentionTime = retentionTime;
            this.logAddress = logAddress;
            return this;
        }

        public XLogConfig build() {
            return new XLogConfig(this);
        }
    }

}
