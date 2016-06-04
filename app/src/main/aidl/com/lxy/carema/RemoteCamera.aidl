// RemoteCamera.aidl
package com.lxy.carema;


interface RemoteCamera {
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void startRemoteCamera();
}
