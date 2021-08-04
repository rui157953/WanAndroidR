package com.ryan.app.log;


import android.util.Log;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.IllegalFormatException;

public class LoggerX implements LogX {

    private final static boolean logFlag = true;
    private final static int logLevel = Log.VERBOSE;

    private final int MAX_LENGTH = 4000;
    private static Hashtable<String, LogX> sLoggerTable = new Hashtable<>();

    private final String mTag;

    public LoggerX(String tag) {
        this.mTag = tag;
    }

    /**
     * @return
     */
    @SuppressWarnings("unused")
    public static LogX getLogX(String className) {
        String key = LogXConfig.get().getPreTag() + className;
        LogX classLogger = sLoggerTable.get(key);
        if (classLogger == null) {
            classLogger = new LoggerX(key);
            sLoggerTable.put(key, classLogger);
        }
        return classLogger;
    }

    /**
     * Get The Current Function Name
     *
     * @return
     */
    private String getMethod() {

        if (!LogXConfig.get().isMethodStack()) return null;

        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }

            return st.getMethodName() + "(" + st.getFileName() + ":" + st.getLineNumber() + ")";
        }
        return null;
    }

    /***
     *
     * printLog:因为logcat输出log的字符长度是4k，超过长度字符串会被丢弃，所以对超过4k长度的log做分段输出. <br/>
     *
     * @author mozk
     * @param level
     * @param logText
     */
    public void printLog(int level, String logText) {
        if (!LogXConfig.get().isLoggable()) return;

        int index = 0;
        String sub;

        logText = logText.trim();

        while (index < logText.length()) {
            // java的字符不允许指定超过总的长度
            if (logText.length() <= index + MAX_LENGTH) {
                sub = logText.substring(index);
            } else {
                sub = logText.substring(index, index + MAX_LENGTH);
            }

            index += MAX_LENGTH;

            switch (level) {
                case Log.INFO:
                    Log.i(mTag, sub.trim());
                    break;
                case Log.DEBUG:
                    Log.d(mTag, sub.trim());
                    break;
                case Log.ERROR:
                    Log.e(mTag, sub.trim());
                    break;
                case Log.WARN:
                    Log.w(mTag, sub.trim());
                    break;
                case Log.VERBOSE:
                    Log.v(mTag, sub.trim());
                    break;
                default:
                    Log.e(mTag, sub.trim());
                    break;
            }
        }
    }

    public String tag() {
        return this.mTag;
    }

    @Override
    public LogXConfig getConfig() {
        return LogXConfig.get();
    }

    public void v(String msg, Object... args) {
        if (logFlag) {
            if (logLevel <= Log.VERBOSE) {
                String name = getMethod();
                if (name != null) {
                    printLog(Log.VERBOSE, this.formattedString(msg, args) + " - " + name);
                } else {
                    printLog(Log.VERBOSE, this.formattedString(msg, args));
                }
            }
        }
    }

    private String formattedString(String msg, Object... args) {
        if (args == null || args.length == 0) {
            return msg;
        }
        try {
            return String.format(msg, args);
        } catch (IllegalFormatException var4) {
            return "Log failed by reason of formatting failed. Message: " + var4.getMessage() + "\nLog content: " + msg + "\nLog content format args: " + Arrays.asList(args).toString();
        }
    }


    public void v(Throwable t, String msg, Object... args) {
        Log.v(this.mTag, this.formattedString(msg, args), t);
    }

    public void v(Throwable t) {
        Log.v(this.mTag, (String) null, t);
    }

    public void d(String msg, Object... args) {
        if (logFlag) {
            if (logLevel <= Log.DEBUG) {
                String name = getMethod();
                if (name != null) {
                    printLog(Log.DEBUG, this.formattedString(msg, args) + " - " + name);
                } else {
                    printLog(Log.DEBUG, this.formattedString(msg, args));
                }
            }
        }
    }

    public void d(Throwable t, String msg, Object... args) {
        Log.d(this.mTag, this.formattedString(msg, args), t);
    }

    public void d(Throwable t) {
        Log.d(this.mTag, (String) null, t);
    }

    public void i(String msg, Object... args) {
        if (logFlag) {
            if (logLevel <= Log.INFO) {
                String name = getMethod();
                if (name != null) {
                    printLog(Log.INFO, this.formattedString(msg, args) + " - " + name);
                } else {
                    printLog(Log.INFO, this.formattedString(msg, args));
                }
            }
        }
    }

    public void i(Throwable t, String msg, Object... args) {
        Log.i(this.mTag, this.formattedString(msg, args), t);
    }

    public void i(Throwable t) {
        Log.i(this.mTag, (String) null, t);
    }

    public void w(String msg, Object... args) {
        if (logFlag) {
            if (logLevel <= Log.WARN) {
                String name = getMethod();
                if (name != null) {
                    printLog(Log.WARN, this.formattedString(msg, args) + " - " + name);
                } else {
                    printLog(Log.WARN, this.formattedString(msg, args));
                }
            }
        }
    }

    public void w(Throwable t, String msg, Object... args) {
        Log.w(this.mTag, this.formattedString(msg, args), t);
    }

    public void w(Throwable t) {
        Log.w(this.mTag, t);
    }

    public void e(String msg, Object... args) {
        if (logFlag) {
            if (logLevel <= Log.ERROR) {
                String name = getMethod();
                if (name != null) {
                    printLog(Log.ERROR, this.formattedString(msg, args) + " - " + name);
                } else {
                    printLog(Log.ERROR, this.formattedString(msg, args));
                }
            }
        }
    }

    public void e(Throwable t, String msg, Object... args) {
        if (logFlag) {
            String method = getMethod();
            Log.e(mTag, "Thread:" + Thread.currentThread().getName() + "[" + method
                    + "]: " + this.formattedString(msg, args) + "\n", t);
        }
    }

    public void e(Throwable t) {
        if (logFlag) {
            if (logLevel <= Log.ERROR) {
                Log.e(this.mTag, (String) null, t);
            }
        }
    }

    public void wtf(String msg, Object... args) {
        Log.wtf(this.mTag, this.formattedString(msg, args));
    }

    public void wtf(Throwable t, String msg, Object... args) {
        Log.wtf(this.mTag, this.formattedString(msg, args), t);
    }

    public void wtf(Throwable t) {
        Log.wtf(this.mTag, t);
    }
}
