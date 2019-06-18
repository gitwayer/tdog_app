package com.tdog.tdog_app.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.tdog.tdog_app.util.CustomProgressDialog;

public abstract class BaseActivity extends Activity {
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected abstract void initView();



    public void showSnackBar(View view, String str, Snackbar.Callback callback) {
        Snackbar.make(view,str,Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 显示进度条
     */
    public void showProgressBar() {
        mDialog = CustomProgressDialog.createLoadingDialog(this, "加载中...");
        mDialog.show();//显示
    }

    /**
     * 隐藏进度条
     */
    public void hideProgressBar() {
        if(mDialog.isShowing()){
            mDialog.cancel();
        }
    }

}
