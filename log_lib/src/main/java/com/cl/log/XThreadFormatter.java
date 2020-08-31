package com.cl.log;

public class XThreadFormatter implements XLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}
