package com.cl.log;

public interface XLogFormatter<T> {

    String format(T data);
}