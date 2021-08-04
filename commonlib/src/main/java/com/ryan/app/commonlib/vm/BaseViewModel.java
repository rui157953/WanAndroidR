package com.ryan.app.commonlib.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    private MutableLiveData<Object> requestFinish = new MutableLiveData<>();
    private boolean showClose = true;

    public BaseViewModel() {
    }

    public MutableLiveData<Object> getRequestFinish() {
        return requestFinish;
    }

    public void showClose(boolean showClose) {
        this.showClose = showClose;
    }

    public boolean isShowClose() {
        return showClose;
    }

    public void close() {
        requestFinish.setValue(null);
    }
}
