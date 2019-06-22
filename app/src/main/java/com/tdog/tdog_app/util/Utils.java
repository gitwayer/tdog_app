package com.tdog.tdog_app.util;

import android.content.Context;
import android.view.View;

public class Utils {
    public static final String SEP = ",";

    public static void exit(Context context) {
        MyAlertDialog alertDialog = new MyAlertDialog(context);
        alertDialog.setTitle("提示");
        alertDialog.setMsg("确定退出？");
        alertDialog.setPositiveButton("确认", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                return;
            }
        });
        alertDialog.setNegativeButton("取消", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                return;
            }
        });
        alertDialog.setCancelable(false);
    }

    public static boolean isEmpty(String bean) {
        if (bean == null || "".equals(bean.trim())
                || "null".equalsIgnoreCase(bean)) {
            return true;
        }
        return false;
    }

}
