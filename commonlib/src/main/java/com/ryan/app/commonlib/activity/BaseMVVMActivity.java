package com.ryan.app.commonlib.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.ryan.app.commonlib.R;
import com.ryan.app.commonlib.databinding.ActivityMvvmBaseBinding;
import com.ryan.app.commonlib.vm.BaseViewModel;

public abstract class BaseMVVMActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity {

    private VM mViewModel;
    private DB mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBaseBinding baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_base);
        mViewModel = new ViewModelProvider(this).get(createViewModel());
        baseBinding.setBaseVM(mViewModel);
        mBinding = DataBindingUtil.bind(LayoutInflater.from(this).inflate(createContentView(), null));
        baseBinding.activityBaseContent.addView(mBinding.getRoot());
        mViewModel.getRequestFinish().observe(this, (o -> finish()));

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }


    @LayoutRes
    protected abstract int createContentView();

    protected abstract Class<VM> createViewModel();

    public VM getViewModel() {
        return mViewModel;
    }

    public DB getBinding() {
        return mBinding;
    }
}