package com.tdog.tdog_app.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.tdog.tdog_app.R;


/**
 * @description:弹出框统一样式
 */
public class MyAlertDialog {
    private Dialog dialog;
    private LinearLayout llBg;
    private TextView tvTitle;
    private TextView tvMsg;
    private Button btnNeg;
    private Button btnPos;
    private ImageView imgSpe;

    private Display display;
    private boolean showTitle = false;// 是否显示标题
    private boolean showMsg = false;// 是否显示提示语
    private boolean showPosBtn = false;// 是否显示确认按钮
    private boolean showNegBtn = false;// 是否显示取消按钮
    private Context context;

    public MyAlertDialog(Context context) {
        this(context, Gravity.NO_GRAVITY, 0, 0, 1.0f);
    }

    public MyAlertDialog(Context context, int gravity, int x, int y, float alpha) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_alert_dialog, null);
        // 获取自定义Dialog布局中的控件
        llBg = (LinearLayout) view.findViewById(R.id.ll_bg);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setVisibility(View.GONE);
        tvMsg = (TextView) view.findViewById(R.id.tv_msg);
        tvMsg.setVisibility(View.GONE);
        btnNeg = (Button) view.findViewById(R.id.btn_neg);
        btnNeg.setVisibility(View.GONE);
        btnPos = (Button) view.findViewById(R.id.btn_pos);
        btnPos.setVisibility(View.GONE);
        imgSpe = (ImageView) view.findViewById(R.id.img_spe);
        imgSpe.setVisibility(View.GONE);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        // 调整dialog背景大小
        llBg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        if (gravity != Gravity.NO_GRAVITY) {
            lp.gravity = gravity;
        }
        lp.x = x;
        lp.y = y;
        lp.alpha = alpha;
        dialogWindow.setAttributes(lp);
    }

    /**
     * 设置弹出框标题
     */
    public void setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            tvTitle.setText("标题");
        } else {
            tvTitle.setText(title);
        }
        show();
    }

    /**
     * 设置提示语
     */
    public void setMsg(String msg) {
        showMsg = true;
        if ("".equals(msg)) {
            tvMsg.setText("内容");
        } else {
            tvMsg.setText(msg);
        }
        show();
    }

    /**
     * dialog弹出后会点击屏幕或物理返回键消失处理
     */
    public void setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        show();
    }

    /**
     * dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
     */
    public void setCanceledOnTouchOutside(boolean isCancel) {
        dialog.setCanceledOnTouchOutside(isCancel);
        show();
    }

    /**
     * 设置确认按钮事件
     */
    public void setPositiveButton(String text, final OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btnPos.setText("确定");
        } else {
            btnPos.setText(text);
        }
        btnPos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        show();
    }

    /**
     * 设置取消按钮
     */
    public void setNegativeButton(String text, final OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btnNeg.setText("取消");
        } else {
            btnNeg.setText(text);
        }
        btnNeg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        show();
    }

    /**
     * 弹出对话框
     */
    private void show() {
        setLayout();
        if (!((Activity) context).isFinishing()) {
            dialog.show();
        }
    }

    /**
     * 设置布局
     */
    private void setLayout() {
        if (!showTitle && !showMsg) {
            tvTitle.setText("提示");
            tvTitle.setVisibility(View.VISIBLE);
        }
        if (showTitle) {
            tvTitle.setVisibility(View.VISIBLE);
        }
        if (showMsg) {
            tvMsg.setVisibility(View.VISIBLE);
        }
        if (!showPosBtn && !showNegBtn) {
            btnPos.setText("确定");
            btnPos.setVisibility(View.VISIBLE);
            btnPos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        if (showPosBtn && showNegBtn) {
            btnPos.setVisibility(View.VISIBLE);
            btnPos.setBackgroundResource(R.drawable.bg_dialog_right_selector);
            btnNeg.setVisibility(View.VISIBLE);
            btnNeg.setBackgroundResource(R.drawable.bg_dialog_left_selector);
            imgSpe.setVisibility(View.VISIBLE);
        }
        if (showPosBtn && !showNegBtn) {
            btnPos.setVisibility(View.VISIBLE);
        }
        if (!showPosBtn && showNegBtn) {
            btnNeg.setVisibility(View.VISIBLE);
        }
    }
}
