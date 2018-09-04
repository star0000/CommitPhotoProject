package com.moyu.photoproject;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by 墨羽 on 2018/7/12.
 */

public class PermisstionUtil {

    /**
     * 0有权限 -1没有权限
     *
     * @param context
     * @param permissions
     * @param requestCode
     */
    public static boolean CheckSelfPermissions(Activity context, String[] permissions, int requestCode) {
        if (permissions != null) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    requestPermisstion(context, permissions, requestCode);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 请求权限
     *
     * @param context
     * @param permissions
     * @param requestCode
     */
    private static void requestPermisstion(Activity context, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(context, permissions, requestCode);
    }

    /**
     * 请求权限结果
     *
     * @param requestCode
     * @param resultCode
     * @param permissions
     * @param grantResults
     * @return
     */
    public static boolean permissionsResult(int requestCode, int resultCode, String[] permissions, int[] grantResults) {
        //请求码对应后开上判断权限是否开启
        if (requestCode == resultCode) {
            for (int i = 0; i < permissions.length; i++) {
                Log.d("permission", "===permissions:" + permissions[i] + "===grantResults:" + grantResults[i]);
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                    return false;
            }
            return true;
        }
        return false;
    }

}
