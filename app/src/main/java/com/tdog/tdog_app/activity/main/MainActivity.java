package com.tdog.tdog_app.activity.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tdog.tdog_app.R;
import com.tdog.tdog_app.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextView mTextMessage;

    //导航栏菜单点击监听
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("111");
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText("222");
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText("333");
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        mTextMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, mTextMessage.getText(), Snackbar.LENGTH_LONG).show();
            }
        });
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public void onClick(View v) {

    }
}
