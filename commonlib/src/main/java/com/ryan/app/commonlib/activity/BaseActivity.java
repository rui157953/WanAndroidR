package com.ryan.app.commonlib.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.permissionx.guolindev.PermissionX;
import com.ryan.app.commonlib.util.Utils;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    private static final ArrayList<String> permissionList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!Utils.isPermissionGranted(this, permissionList)) {
            PermissionX.init(this).permissions(permissionList)
                    .onExplainRequestReason((scope, deniedList) ->
                            scope.showRequestReasonDialog(deniedList, "Core fundamental are based on these permissions", "OK", "Cancel"))
                    .request((allGranted, grantedList, deniedList) -> {
                        if (allGranted) {
                            Toast.makeText(BaseActivity.this, "All permissions are granted", Toast.LENGTH_LONG).show();
                            onPermissionDone();
                        } else {
                            Toast.makeText(BaseActivity.this, "These permissions are denied:" + deniedList, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
        } else {
            onPermissionDone();
        }
    }

    private void onPermissionDone() {
        // TODO: 2021/7/15 条件跳转
    }

    public void startActivity(Class<? extends Activity> aClass) {
        startActivity(aClass, null, null);
    }

    public void startActivity(Class<? extends Activity> aClass, String[] paramKeys, String[] paramValues) {
        Intent intent = new Intent(this, aClass);
        if (paramKeys != null && paramValues != null && paramKeys.length == paramValues.length) {
            for (int i = 0; i < paramKeys.length; i++) {
                intent.putExtra(paramKeys[i], paramValues[i]);
            }
        }
        startActivity(intent);
    }

    public void startActivityWithParcelable(Class<? extends Activity> aClass, String[] paramKeys, Parcelable[] paramValues) {
        Intent intent = new Intent(this, aClass);
        if (paramKeys != null && paramValues != null) {
            for (int i = 0; i < paramKeys.length; i++) {
                intent.putExtra(paramKeys[i], paramValues[i]);
            }
        }
        startActivity(intent);
    }

    public String getStringParam(String key, String def) {
        String param = getIntent().getStringExtra(key);
        return param == null ? def : param;
    }

    public <T extends Parcelable> T getParcelableParam(String key, Class<T> aClass) {
        return getIntent().getParcelableExtra(key);
    }
}