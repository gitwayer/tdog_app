package com.tdog.tdog_app.activity.login;

import android.content.Intent;
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
import com.tdog.tdog_app.activity.main.MainActivity;
import com.tdog.tdog_app.activity.map.MapActivity;
import com.tdog.tdog_app.base.BaseActivity;

import java.lang.annotation.Retention;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

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
        Toast.makeText(this,"register",Toast.LENGTH_SHORT).show();
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
}
