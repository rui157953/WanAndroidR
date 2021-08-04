package com.ryan.app.wanandroidr;


import android.os.Bundle;

import com.ryan.app.commonlib.activity.BaseMVVMActivity;
import com.ryan.app.commonlib.vm.BaseViewModel;
import com.ryan.app.log.LogX;
import com.ryan.app.log.LoggerX;
import com.ryan.app.wanandroidr.databinding.ActivityMainBinding;

public class MainActivity extends BaseMVVMActivity<ActivityMainBinding, BaseViewModel> {
    private static final LogX LOGX = LoggerX.getLogX("MainActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGX.d("adsfasdfag");
    }

    @Override
    protected int createContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<BaseViewModel> createViewModel() {
        return BaseViewModel.class;
    }
}