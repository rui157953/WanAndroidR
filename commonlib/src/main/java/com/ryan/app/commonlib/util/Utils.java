package com.ryan.app.commonlib.util;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.List;

public class Utils {
    public static boolean isPermissionGranted(Context context, @NonNull List<String> permissions) {
        boolean granted = true;
        for (String permission : permissions) {
            if (!isPermissionGranted(context, permission)) {
                granted = false;
            }
        }
        return granted;
    }

    public static boolean isPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
