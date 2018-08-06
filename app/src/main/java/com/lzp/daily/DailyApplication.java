package com.lzp.daily;

import com.lzp.core.AppRuntime;
import com.lzp.core.CrashHandler;
import com.lzp.core.base.BaseApplication;

public class DailyApplication extends BaseApplication {
    @Override
    public AppRuntime createAppRuntime() {
        return new AppRuntime();
    }

    @Override
    public boolean monitoNetwork() {
        return true;
    }

    @Override
    public CrashHandler getCrashHandler() {
        return new CrashHandler();
    }
}
