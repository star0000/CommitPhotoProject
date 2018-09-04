package com.moyu.photoproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button getPhoto;
    private int type;
    private String[] permistions = new String[]{WRITE_EXTERNAL_STORAGE, CAMERA};
    private int requestPermistion = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPhoto = (Button) findViewById(R.id.getPhoto);
        getPhoto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getPhoto:
                PhotoDialog photoDialog = new PhotoDialog();
                photoDialog.show(getFragmentManager(),"PhotoDialog");
                break;
        }
    }

    /**
     * @param type 1.拍照 2.相册
     */
    public void showPicDialog(int type) {
        this.type = type;
        if (PermisstionUtil.CheckSelfPermissions(this, permistions, requestPermistion)) {
            if (type == ContentValue.PICUTER) {
                PictureSelectorUtil.toPicutre(this);
            } else if (type == ContentValue.PHOTO) {
                PictureSelectorUtil.toPhoto(this);
            }
        }
    }


    /**
     * 上传图片
     */
    private void uploadImg(String path) {
        String bitmapPath = CompressUtil.decodeSampledBitmapFromResource(path, DisplayUtil.dp2px(MainActivity.this, 130), DisplayUtil.dp2px(MainActivity.this, 130));
        if (bitmapPath != null && !bitmapPath.equals("")) {
            File file = new File(bitmapPath);
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part MultipartFile =
                    MultipartBody.Part.createFormData("file", file.getName(), requestFile);
         //   presenter.uploadSignFile(uploadFile, MultipartFile, ConstantValue.STARBARUP);
        } else {
            Toast.makeText(MainActivity.this, "找不到此图片", Toast.LENGTH_SHORT).show();
        }
    }


}
