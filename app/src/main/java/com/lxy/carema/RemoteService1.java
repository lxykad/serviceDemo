package com.lxy.carema;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.MediaStore;

public class RemoteService1 extends Service {

    private IBinder mBindr = new RemoteCamera.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void startRemoteCamera() throws RemoteException {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    };

    public RemoteService1() {

    }


    @Override
    public IBinder onBind(Intent intent) {

        return mBindr;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Notification notification = new Notification
                .Builder(getApplicationContext())
                .setContentTitle("远程服务")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("我是远程相机服务")
                .build();

        Notification notice = new Notification();
        notice.iconLevel = R.mipmap.ic_launcher;
        notice.tickerText = "220";
        startForeground(startId, notification);
        return super.onStartCommand(intent, flags, startId);
    }


    public RemoteService1 getService1() {
        return this;
    }

}
