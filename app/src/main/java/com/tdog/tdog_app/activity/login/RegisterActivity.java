package com.tdog.tdog_app.activity.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tdog.library.annotation.ContentView;
import com.tdog.library.annotation.InjectView;
import com.tdog.library.annotation.OnClick;
import com.tdog.tdog_app.R;
import com.tdog.tdog_app.activity.main.MainActivity;
import com.tdog.tdog_app.activity.map.MapActivity;
import com.tdog.tdog_app.base.BaseActivity;
import com.tdog.tdog_app.net.HttpCallBack;
import com.tdog.tdog_app.net.HttpHelper;
import com.tdog.tdog_app.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

    private static String TAG;
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

    @InjectView(R.id.pb_view)
    private ProgressBar pbView;

    @OnClick(R.id.rl_back)
    public void back(View view){
        onBackPressed();
    }

    @OnClick(R.id.btn_register)
    public void register(View view){
        if(checkParams()){
            requestHttpRegister();
        }
    }

    private boolean checkParams() {
        if(Utils.isEmpty(etUser.getText().toString().trim())){
            Snackbar.make(etUser,NOUSER,Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if(Utils.isEmpty(etPassword.getText().toString().trim())){
            Snackbar.make(etPassword,NOPASSWORD,Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if(Utils.isEmpty(etCompanyName.getText().toString().trim())){
            Snackbar.make(etCompanyName,NOCOMPANYNAME,Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if(Utils.isEmpty(etCompanyAddress.getText().toString().trim())){
            Snackbar.make(etCompanyAddress,NOCOMPANYADDRESS,Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if(Utils.isEmpty(etPrincipalName.getText().toString().trim())){
            Snackbar.make(etPrincipalName,NOPRINCIPALNAME,Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if(Utils.isEmpty(etVerifyCode.getText().toString().trim())){
            Snackbar.make(etPrincipalName,NOVERIFYCODE,Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @OnClick(R.id.img_pos)
    public void jumpToMap(View view){
        Intent intent = new Intent(this,MapActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initView() {
        TAG = this.getClass().getName();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 注册
     */
    private void requestHttpRegister(){
        //默认坐标
        double x = 116.4034;
        double y = 39.9282;

        String url = "http://182.92.200.237:8000/AppServer/getDeviceDao";
        String param ="'"+etUser.getText().toString().trim()+"'"+Utils.SEP+"'"+etPassword.getText().toString().trim()+"'"+Utils.SEP+1+Utils.SEP+
                "'"+"'"+Utils.SEP+"'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'"+Utils.SEP+"'"+
                etPrincipalName.getText().toString().trim()+"'"+Utils.SEP+"'"+etCompanyName.getText().toString().trim()+"'"+
                Utils.SEP+"'"+etCompanyAddress.getText().toString().trim()+"'"+Utils.SEP+"'"+etPrincipalName.getText().toString().trim()+"'"+
                Utils.SEP+"'"+x+"'"+Utils.SEP+"'"+y+"'";
        Log.e(TAG,"param："+param);
        Map<String,Object> map = new HashMap<>();
        map.put("action","register");
        map.put("type","1");
        map.put("param",param);
        HttpHelper.getInstance().post(url, map , new HttpCallBack<ResponseEntity>() {
            @Override
            public void onSuccess(ResponseEntity objResult) {
                pbView.setVisibility(View.GONE);
                if(objResult==null){
                    Toast.makeText(RegisterActivity.this,"json 解析异常",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this,objResult.toString(),Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure() {
                pbView.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
