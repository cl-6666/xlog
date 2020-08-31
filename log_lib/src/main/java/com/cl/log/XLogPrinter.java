package com.cl.log;

import androidx.annotation.NonNull;

public interface XLogPrinter {
    void print(@NonNull XLogConfig config, int level, String tag, @NonNull String printString);
}
