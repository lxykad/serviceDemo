package com.lxy.carema;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean mIsBind;
    private boolean mIsRemoteBind;
    private Service1.MyBinder mBinder;

    //本地服务
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIsBind = true;
            mBinder = (Service1.MyBinder) service;
            mBinder.bind("第一个activity绑定了service1");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBind = false;
            mBinder = null;
        }
    };
    //远程服务
    private RemoteCamera mRemoteBinder;
    private ServiceConnection mRemoteConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIsRemoteBind = true;
            mRemoteBinder = RemoteCamera.Stub.asInterface(service);
            //mRemoteBinder = (RemoteCamera) service;//类型强制转换异常
            try {
                mRemoteBinder.startRemoteCamera();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsRemoteBind = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Service1.class);
                startService(intent);
                bindService(intent, mConnection, BIND_AUTO_CREATE);
            }
        });

        //绑定远程服务
        Button bt3 = (Button) findViewById(R.id.bt3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RemoteService1.class);
                startService(intent);
                bindService(intent, mRemoteConnection, BIND_AUTO_CREATE);
            }
        });

    }

    //
    public void goSecond(View view) {
        Intent intent = new Intent("second");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIsBind) {
            unbindService(mConnection);
        }
        if(mIsRemoteBind){
           unbindService(mRemoteConnection);
        }
    }
}
