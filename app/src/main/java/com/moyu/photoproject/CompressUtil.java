package com.moyu.photoproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by 墨羽 on 2018/7/12.
 * 当前类的注释: 压缩图片
 */

public class CompressUtil {

    /**
     * 获取手机最大内存
     *
     * @return
     */
    public static int getPhoneMemory() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        return maxMemory;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static String decodeSampledBitmapFromResource(String file,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return writeFileToLocal(BitmapFactory.decodeFile(file, options));
    }

    /**
     * 文件写到本地
     *
     * @param bitmap
     * @return
     */
    public static String writeFileToLocal(Bitmap bitmap) {
        String bitmapPath = Environment.getExternalStorageDirectory().getPath()
                + "/" + bitmap.hashCode() + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory(), bitmap.hashCode() + ".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Boolean state = bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return bitmapPath;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error", "error:" + e);
            return "";
        }
    }

}
