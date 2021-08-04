package com.ryan.app.log;

public interface LogX {
    String tag();

    LogXConfig getConfig();

    void v(String var1, Object... var2);

    void v(Throwable var1, String var2, Object... var3);

    void v(Throwable var1);

    void d(String var1, Object... var2);

    void d(Throwable var1, String var2, Object... var3);

    void d(Throwable var1);

    void i(String var1, Object... var2);

    void i(Throwable var1, String var2, Object... var3);

    void i(Throwable var1);

    void w(String var1, Object... var2);

    void w(Throwable var1, String var2, Object... var3);

    void w(Throwable var1);

    void e(String var1, Object... var2);

    void e(Throwable var1, String var2, Object... var3);

    void e(Throwable var1);

    void wtf(String var1, Object... var2);

    void wtf(Throwable var1, String var2, Object... var3);

    void wtf(Throwable var1);

}
