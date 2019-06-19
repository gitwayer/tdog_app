package com.tdog.tdog_app.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tdog.tdog_app.R;

public class CustomProgressDialog {
    /**
     * 得到自定义的progressDialog
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.view_loading_dialog, null);
        LinearLayout llLoading = (LinearLayout) v.findViewById(R.id.ll_loading);
        ImageView imgLoading = (ImageView) v.findViewById(R.id.img_loading);
        TextView tvTip = (TextView) v.findViewById(R.id.tv_tip);// 提示文字
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_loading);

        imgLoading.startAnimation(hyperspaceJumpAnimation);
        tvTip.setText(msg);

        Dialog loadingDialog = new Dialog(context, R.style.dialog_loading);
        loadingDialog.setCancelable(false);
        loadingDialog.setContentView(llLoading, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        return loadingDialog;

    }

}
