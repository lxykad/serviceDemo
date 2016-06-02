package com.lxy.carema;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private boolean mIsBind;
    private Service1.MyBinder mBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIsBind = true;
            mBinder = (Service1.MyBinder) service;
            mBinder.bind("第二个activity绑定了service1");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBind = false;
            mBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initEvent();
    }

    private void initEvent() {
        Button bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, Service1.class);
                bindService(intent, mConnection, BIND_AUTO_CREATE);
            }
        });
    }
}
