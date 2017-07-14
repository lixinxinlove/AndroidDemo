package com.lee.androiddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.lee.androiddemo.ILeeAidlInterface;

public class LeeService extends Service {

    public LeeService() {
    }


    ILeeAidlInterface.Stub binder = new ILeeAidlInterface.Stub() {
        @Override
        public int add(int x, int y) throws RemoteException {
            return x + y;
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
