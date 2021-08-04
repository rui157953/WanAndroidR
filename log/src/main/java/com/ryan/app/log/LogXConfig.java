package com.ryan.app.log;

import android.text.TextUtils;

public class LogXConfig {
    private static LogXConfig sLogXConfig = new LogXConfig("");
    private String preTag;
    private boolean isLoggable = true;
    private boolean methodStack = true;

    private LogXConfig(String preTag) {
        this.preTag = preTag;
    }

    public String getPreTag() {
        return preTag;
    }

    public boolean isLoggable() {
        return isLoggable;
    }

    public boolean isMethodStack() {
        return methodStack;
    }

    public static void reset(LogXConfig logXConfig) {
        sLogXConfig = logXConfig;
    }

    public static LogXConfig get() {
        return sLogXConfig;
    }

    public static class Builder {
        private String preTag;
        private boolean isLoggable = true;
        private boolean methodStack = true;

        public Builder setPreTag(String preTag) {
            if (preTag == null || preTag.isEmpty()) {
                throw new IllegalArgumentException("preTag is null");
            }
            this.preTag = preTag;
            return this;
        }

        public Builder setLoggable(boolean loggable) {
            this.isLoggable = loggable;
            return this;
        }

        public Builder enableMethodStack(boolean methodStack) {
            this.methodStack = methodStack;
            return this;
        }

        public LogXConfig build() {
            LogXConfig logXConfig = new LogXConfig("");
            if (!TextUtils.isEmpty(this.preTag)) {
                logXConfig.preTag = this.preTag + "|";
            }
            logXConfig.isLoggable = this.isLoggable;
            logXConfig.methodStack = this.methodStack;
            return logXConfig;
        }
    }
}
