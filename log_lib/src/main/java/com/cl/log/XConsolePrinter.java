package com.cl.log;

import android.util.Log;

import androidx.annotation.NonNull;

import static com.cl.log.XLogConfig.MAX_LEN;
public class XConsolePrinter implements XLogPrinter {

    @Override
    public void print(@NonNull XLogConfig config, int level, String tag, @NonNull String printString) {
        int length = printString.length();
        //一共有countOfSub行
        int countOfSub = length/ XLogConfig.MAX_LEN;

        if (countOfSub > 0) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (int x = 0; x < countOfSub; x++) {
                //裁剪字符串
                String substring = printString.substring(index, index + XLogConfig.MAX_LEN);
                sb.append(substring);
                index += XLogConfig.MAX_LEN;
            }
            if(index!=length){
                sb.append(printString.substring(index,length));
            }
            //调用官方的Log打印拼接好的字符串
            Log.println(level, tag, sb.toString());
        }else{
            Log.println(level, tag, printString);
        }
    }
}
