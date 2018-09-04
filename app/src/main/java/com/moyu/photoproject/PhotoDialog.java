package com.moyu.photoproject;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by 墨羽 on 2018/7/12.
 */

public class PhotoDialog extends DialogFragment implements View.OnClickListener {

    private View dialogView;
    private TextView tvPicture;
    private TextView tvPhoto;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        dialogView = inflater.inflate(R.layout.layout_select_photo, container);
        tvPicture = dialogView.findViewById(R.id.tv_picture);
        tvPhoto = dialogView.findViewById(R.id.tv_photo);
        tvPicture.setOnClickListener(this);
        tvPhoto.setOnClickListener(this);
        return dialogView;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getDialog().getWindow().setLayout(displayMetrics.widthPixels,getDialog().getWindow().getAttributes().height);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.windowAnimations = R.style.bottomSheet_animation;
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);
        return dialog;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_picture:
                if(getActivity() instanceof MainActivity){
                    ((MainActivity) getActivity()).showPicDialog(ContentValue.PICUTER);
                }
                getDialog().dismiss();
               break;
            case R.id.tv_photo:
                if(getActivity() instanceof MainActivity){
                    ((MainActivity) getActivity()).showPicDialog(ContentValue.PHOTO);
                }
                getDialog().dismiss();
               break;
        }

    }

}
