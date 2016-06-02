package com.lxy.carema;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;

public class Service1 extends Service {


    public Service1() {

    }

    @Override
    public IBinder onBind(Intent intent) {

        return new MyBinder();
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
                .setContentTitle("service1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("歌曲")
                .build();

        Notification notice = new Notification();
        notice.iconLevel = R.mipmap.ic_launcher;
        notice.tickerText = "220";
        startForeground(startId, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    public Service1 getService1() {
        return this;
    }

    class MyBinder extends Binder {

        public void bind(String str) {
            System.out.println("bind==============" + str);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}
