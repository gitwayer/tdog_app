package com.tdog.tdog_app.activity.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tdog.library.annotation.ContentView;
import com.tdog.library.annotation.InjectView;
import com.tdog.library.annotation.OnClick;
import com.tdog.tdog_app.R;
import com.tdog.tdog_app.activity.map.MapActivity;
import com.tdog.tdog_app.base.BaseActivity;
import com.tdog.tdog_app.util.Utils;

import java.lang.annotation.Retention;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

    private static final String NOUSER = "手机号不能为空";
    private static final String NOPASSWORD = "密码不能为空";
    private static final String NOCOMPANYNAME = "单位名称不能为空";
    private static final String NOCOMPANYADDRESS = "单位地址不能为空";
    private static final String NOPRINCIPALNAME = "负责人姓名不能为空";
    private static final String NOVERIFYCODE = "验证码不能为空";

    @InjectView(R.id.rl_back)
    private RelativeLayout rlBack;

    @InjectView(R.id.btn_register)
    private Button btnRegister;

    @InjectView(R.id.img_pos)
    private ImageView imgPos;

    @InjectView(R.id.et_user)
    private EditText etUser;

    @InjectView(R.id.et_password)
    private EditText etPassword;

    @InjectView(R.id.et_company_name)
    private EditText etCompanyName;

    @InjectView(R.id.et_company_address)
    private EditText etCompanyAddress;

    @InjectView(R.id.et_principal_name)
    private EditText etPrincipalName;

    @InjectView(R.id.et_verify_code)
    private EditText etVerifyCode;


    @OnClick(R.id.rl_back)
    public void back(View view){
        onBackPressed();
    }

    @OnClick(R.id.btn_register)
    public void register(View view){
        checkParams();
        Toast.makeText(this,"register",Toast.LENGTH_SHORT).show();
    }

    private void checkParams() {
        if(Utils.isEmpty(etUser.getText().toString().trim())){
            Snackbar.make(etUser,NOUSER,Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(Utils.isEmpty(etPassword.getText().toString().trim())){
            Snackbar.make(etPassword,NOPASSWORD,Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(Utils.isEmpty(etCompanyName.getText().toString().trim())){
            Snackbar.make(etCompanyName,NOCOMPANYNAME,Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(Utils.isEmpty(etCompanyAddress.getText().toString().trim())){
            Snackbar.make(etCompanyAddress,NOCOMPANYADDRESS,Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(Utils.isEmpty(etPrincipalName.getText().toString().trim())){
            Snackbar.make(etPrincipalName,NOPRINCIPALNAME,Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(Utils.isEmpty(etVerifyCode.getText().toString().trim())){
            Snackbar.make(etPrincipalName,NOVERIFYCODE,Snackbar.LENGTH_SHORT).show();
            return;
        }
    }

    @OnClick(R.id.img_pos)
    public void jumpToMap(View view){
        Intent intent = new Intent(this,MapActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initView() {
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
