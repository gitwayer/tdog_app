package com.tdog.tdog_app.activity.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tdog.tdog_app.R;
import com.tdog.tdog_app.activity.main.MainActivity;
import com.tdog.tdog_app.base.BaseActivity;
import com.tdog.tdog_app.net.HttpCallBack;
import com.tdog.tdog_app.net.HttpHelper;
import com.tdog.tdog_app.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity{

    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvRestPassword;
    private RelativeLayout rlBackground;
    private ProgressBar pbView;
    private static final String SEP = ",";

    private static final String NOUSER = "手机号不能为空";
    private static final String NOPASSWORD = "密码不能为空";//

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        TextView tvTitle = (TextView) this.findViewById(R.id.tv_title);
        tvTitle.setText("登录注册");
        RelativeLayout rlBack = (RelativeLayout) this.findViewById(R.id.rl_back);
        rlBack.setVisibility(View.GONE);
        etUserName = (EditText) this.findViewById(R.id.et_user);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        btnLogin = (Button) this.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        tvRegister = (TextView) this.findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(this);
        tvRestPassword = (TextView) this.findViewById(R.id.tv_reset_password);
        tvRestPassword.setOnClickListener(this);
        rlBackground = (RelativeLayout)this.findViewById(R.id.rl_background);
        pbView = (ProgressBar)this.findViewById(R.id.pb_view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Login(v);
                break;
            case R.id.tv_register:
                jumpToRegister();
                break;
            case R.id.tv_reset_password:
                reset();
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     */
    private void Login(View view) {
        pbView.setVisibility(View.VISIBLE);
        if(checkParam(view)) {
            requestHttpLogin();
        }

    }

    /**
     * http请求
     */
    private void requestHttpLogin() {
        String url = "http://182.92.200.237:8000/AppServer/getDeviceDao";
        String param ="'"+etUserName.getText().toString()+"'"+SEP+"'"+etPassword.getText().toString()+"'";
        Map<String,Object> map = new HashMap<>();
        map.put("action","login");
        map.put("type","1");
        map.put("param",param);
        HttpHelper.getInstance().post(url, map , new HttpCallBack<ResponseEntity>() {
            @Override
            public void onSuccess(ResponseEntity objResult) {
                pbView.setVisibility(View.GONE);
                if(objResult==null){
                    Toast.makeText(LoginActivity.this,"json 解析异常",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this,objResult.toString(),Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure() {
                pbView.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
//                Intent intent  = new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(intent);
            }
        });
    }


    /**
     * 检查参数
     * @param view
     * @return
     */
    private boolean checkParam(View view) {
        if(etUserName.getText().toString().trim().equals("")){
            Snackbar.make(view,NOUSER,Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if(etPassword.getText().toString().trim().equals("")){
            Snackbar.make(view,NOPASSWORD,Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 跳转到注册
     */
    private void jumpToRegister() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 重置
     */
    private void reset() {

    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Utils.exit(this);
    }
}
