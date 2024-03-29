package com.cl.log;

/**
 * 堆栈信息格式化
 */
public class XStackTraceFormatter implements XLogFormatter<StackTraceElement[]> {
    @Override
    public String format(StackTraceElement[] stackTrace) {
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        } else if (stackTrace.length == 1) {
            return "\t-" + stackTrace[0].toString();
        } else {
            StringBuilder sb = new StringBuilder();
            //堆栈信息的长度
            int len = stackTrace.length;
            for (int x = 0; x < len; x++) {
                //索引为0
                if (x == 0) {
                    sb.append("stackTrace:  \n");
                }
                //索引为最后一位
                if (x == len - 1) {
                    sb.append("\t└ ");
                    sb.append(stackTrace[x].toString());
                } else {
                    //中间的元素
                    sb.append("\t├ ");
                    sb.append(stackTrace[x].toString());
                    sb.append("\n");
                }
            }
            return sb.toString();
        }
    }
}
